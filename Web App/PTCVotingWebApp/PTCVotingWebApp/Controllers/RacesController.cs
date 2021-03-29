using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Newtonsoft.Json;
using PTCVotingWebApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Controllers
{
    public class RacesController : Controller
    {
        private readonly voteShieldContext _context;
        string[] states = { "Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming" };

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

        private void parseRunners(string runners, string city, string state, string race)
        {
            Politcian politician = new Politcian();

            string[] runnersSplit = runners.Split(";");
            PoliticianHolder.ClearPoliticians();

            for (int i = 0; i < runnersSplit.Length-1; i++)
            {
                string[] temp = runnersSplit[i].Split("~");
                politician = new Politcian();
                politician.city = city;
                politician.state = state;
                politician.title = race;
                try
                {
                    politician.name = temp[0];
                    politician.party = temp[1];
                    if (temp[2].Equals("NoDesc"))
                    {
                        politician.description = "";
                    }
                    else
                    {
                        politician.description = temp[2];
                    }
                }
                catch (Exception ex)
                {
                    Console.WriteLine(ex.Message);
                }
                PoliticianHolder.AddPolitician(politician);
            }
        }

        [HttpPost]
        public async Task<IActionResult> EditPole(string race, string state, string city, string save = null, string runners = null)
        {
            var searched = from b in _context.Politcal
                           select b;

            ViewBag.pol = searched.ToArray();

            if (save != null)
            {
                if (save.Equals("Save"))
                {
                    int i = deletePole(race, state, city);
                    parseRunners(runners, city, state, race);
                    await AddToDbsAsync();
                    clearDups();
                    Task b = Task.Run(() => {
                        Task.Delay(5000).Wait();
                        Console.WriteLine("Task ended delay...");
                    });
                }

                string authInfo1 = username + ":" + password;
                authInfo1 = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo1));

                string api1 = "https://ptcvotingapi.azurewebsites.net/getRaces";
                var webClient1 = new WebClient();
                webClient1.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo1);
                string rawJSON1 = webClient1.DownloadString(api1);


                rawJSON1 = "{ \"Holder\": " + rawJSON1 + "}";
                PoleHolderModel poles1 = JsonConvert.DeserializeObject<PoleHolderModel>(rawJSON1);
                ViewBag.poles = poles1.Holder.ToArray();
                ViewData["test"] = rawJSON1 + " " + authInfo1;
                return View("Poles");
            }
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

        public void clearDups()
        {
            string authInfo = username + ":" + password;
            authInfo = Convert.ToBase64String(System.Text.ASCIIEncoding.ASCII.GetBytes(authInfo));

            string api = $"https://ptcvotingapi.azurewebsites.net/clearPol";
            var webClient = new WebClient();
            webClient.Headers.Add(HttpRequestHeader.Authorization, "Basic " + authInfo);
            string rawJSON = webClient.DownloadString(api);
        }

        public IActionResult FormCreate()
        {
            clearDups();
            Task t = Task.Run(() => {
                Task.Delay(5000).Wait();
                Console.WriteLine("Task ended delay...");
            });

            var searched = from b in _context.Politcal
                           select b;

            ViewBag.pol = searched.ToArray();
            ViewBag.states = states;

            return View();
        }

        //TODO - redo this to match current data, do it in a function to be called also by the edit
        [HttpPost]
        public async Task<IActionResult> FormCreateAsync(string race, string state, string city, string save = null, string runners = null)
        {
            var searched = from b in _context.Politcal
                           select b;

            ViewBag.pol = searched.ToArray();

            if (save != null)
            {
                if (save.Equals("Save"))
                {
                    int i = deletePole(race, state, city);
                    parseRunners(runners, city, state, race);
                    await AddToDbsAsync();
                    clearDups();
                    Task b = Task.Run(() => {
                        Task.Delay(5000).Wait();
                        Console.WriteLine("Task ended delay...");
                    });
                }
            }
            ViewBag.states = states;
            return View();
        }

        public async Task AddToDbsAsync()
        {
            string title;
            Race race = new Race();
            Politcal politcal = new Politcal();
            foreach (var pol in PoliticianHolder.ListPoliticians)
            {
                int locationID = getLocationID(pol.state, pol.city);
                title = pol.title;
                race = new Race();
                politcal = new Politcal();

                politcal.FkId = "0";
                politcal.PoliticalParty = pol.party;
                politcal.Name = pol.name;
                politcal.Description = pol.description;
                _context.Add(politcal);
                await _context.SaveChangesAsync();

                var searched = from b in _context.Politcal
                               select b;
                searched = searched.Where(b => b.Name.Contains(pol.name));

                var array = searched.ToArray();

                race.Race1 = title;
                race.FkPolitcal = array[0].Politcal1;
                race.LocationId = locationID;
                _context.Add(race);
                await _context.SaveChangesAsync();
            }
            
        }

        public int getLocationID(string state, string city)
        {
            return 2;
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
