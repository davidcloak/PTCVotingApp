using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PTCVotingWebApp.Models;
using System;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Controllers
{
    public class RacesController : Controller
    {
        private readonly voteShieldContext _context;


        public RacesController(voteShieldContext context)
        {
            _context = context;
        }

        string username = "ShhhImASecret";
        string password = "ShhhImABiggerSecret123@";

        public int deletePole(string race, string state, string city)
        {
            string authInfo = username + ":" + password;
            authInfo = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo));

            string api = $"https://ptcvotingapi.azurewebsites.net/deleteRace?state={state}&city={city}&name={race}";
            var webClient = new WebClient();
            webClient.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo);
            string rawJSON = webClient.DownloadString(api);
            Console.WriteLine(rawJSON);
            return 1;
        }

        [HttpPost]
        public IActionResult Poles(string race, string state, string city)
        {
            int i = deletePole(race, state, city);

            //short delay for web service to catch Up??? Maybe delay needed though
            Task t = Task.Run(() => {
                Task.Delay(10000).Wait();
                Console.WriteLine("Task ended delay...");
            });

            string authInfo = username + ":" + password;
            authInfo = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo));

            string api = "https://ptcvotingapi.azurewebsites.net/getRaces";
            var webClient = new WebClient();
            webClient.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo);
            string rawJSON = webClient.DownloadString(api);


            rawJSON = "{ \"Holder\": " + rawJSON + "}";
            PoleHolderModel poles = JsonConvert.DeserializeObject<PoleHolderModel>(rawJSON);
            ViewBag.poles = poles.Holder.ToArray();
            ViewData["test"] = rawJSON + " " + authInfo;
            return View();
        }

        public IActionResult Poles()
        {
            string authInfo = username + ":" + password;
            authInfo = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo));

            string api = "https://ptcvotingapi.azurewebsites.net/getRaces";
            var webClient = new WebClient();
            webClient.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo);
            string rawJSON = webClient.DownloadString(api);


            rawJSON = "{ \"Holder\": " + rawJSON + "}";
            PoleHolderModel poles = JsonConvert.DeserializeObject<PoleHolderModel>(rawJSON);
            ViewBag.poles = poles.Holder.ToArray();
            ViewData["test"] = rawJSON + " " + authInfo;
            return View();
        }

        [HttpPost]
        public IActionResult EditPole(string race, string state, string city)
        {
            string authInfo = username + ":" + password;
            authInfo = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo));

            string api = $"https://ptcvotingapi.azurewebsites.net/getSRace?state={state}&city={city}&name={race}";
            var webClient = new WebClient();
            webClient.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo);
            string rawJSON = webClient.DownloadString(api);

            rawJSON = "{ \"Holder\": " + rawJSON + "}";
            PoleHolderModel poles = JsonConvert.DeserializeObject<PoleHolderModel>(rawJSON);
            
            ViewBag.poles = poles.Holder.ToArray();
            ViewData["test"] = rawJSON + " " + authInfo;
            return View();
        }


        public IActionResult FormCreate()
        {
            PoliticianHolder.ClearPoliticians();
            ViewData["update"] = "false";
            ViewData["HasOnePol"] = "false";
            return View();
        }

        //TODO - redo this to match current data, do it in a function to be called also by the edit
        [HttpPost]
        public async Task<IActionResult> FormCreateAsync(Politcian politician, string save = null, string edit = null, string Done = null)
        {
            if (Done != null)
            {
                if (ModelState.IsValid)
                {
                    PoliticianHolder.AddPolitician(politician);
                }
                //should find someway to tell them if it is wrong for now asume the last is right

                //call function to add values to databse
                await AddToDbsAsync();

                PoliticianHolder.ClearPoliticians();
                ViewData["update"] = "false";
                ViewData["HasOnePol"] = "false";
            }
            else
            {
                ViewData["oldName"] = "";
                if (save != null)
                {
                    PoliticianHolder.UpdatePoliticians(save, politician);
                }
                else
                {
                    if (ModelState.IsValid)
                    {
                        PoliticianHolder.AddPolitician(politician);
                    }
                }

                if (edit != null)
                {
                    ViewData["update"] = "true";
                    ViewData["oldName"] = edit;
                }
                else
                {
                    ViewData["update"] = "false";
                }


                ViewData["HasOnePol"] = "true";
                ViewBag.PoliticianHolder = PoliticianHolder.ListPoliticians;
            }
            return View();
        }

        public async Task AddToDbsAsync()
        {
            string title;
            Race race = new Race();
            Politcal politcal = new Politcal();
            foreach (var pol in PoliticianHolder.ListPoliticians)
            {
                title = pol.title;
                race = new Race();
                politcal = new Politcal();

                politcal.FkId = "0";
                politcal.PoliticalParty = pol.party;
                politcal.Name = pol.name;
                _context.Add(politcal);
                await _context.SaveChangesAsync();

                var searched = from b in _context.Politcal
                               select b;
                searched = searched.Where(b => b.Name.Contains(pol.name));

                var array = searched.ToArray();

                race.Race1 = title;
                race.FkPolitcal = array[0].Politcal1;
                _context.Add(race);
                await _context.SaveChangesAsync();
            }
        }


        // GET: Races
        public async Task<IActionResult> Index()
        {
            return View(await _context.Race.ToListAsync());
        }

        // GET: Races/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var race = await _context.Race
                .FirstOrDefaultAsync(m => m.Pk == id);
            if (race == null)
            {
                return NotFound();
            }

            return View(race);
        }

        // GET: Races/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Races/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Race1,FkPolitcal,Pk")] Race race)
        {
            if (ModelState.IsValid)
            {
                _context.Add(race);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(race);
        }

        // GET: Races/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var race = await _context.Race.FindAsync(id);
            if (race == null)
            {
                return NotFound();
            }
            return View(race);
        }

        // POST: Races/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Race1,FkPolitcal,Pk")] Race race)
        {
            if (id != race.Pk)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(race);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!RaceExists(race.Pk))
                    {
                        return NotFound();
                    }
                    else
                    {
                        throw;
                    }
                }
                return RedirectToAction(nameof(Index));
            }
            return View(race);
        }

        // GET: Races/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var race = await _context.Race
                .FirstOrDefaultAsync(m => m.Pk == id);
            if (race == null)
            {
                return NotFound();
            }

            return View(race);
        }

        // POST: Races/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var race = await _context.Race.FindAsync(id);
            _context.Race.Remove(race);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool RaceExists(int id)
        {
            return _context.Race.Any(e => e.Pk == id);
        }
    }
}
