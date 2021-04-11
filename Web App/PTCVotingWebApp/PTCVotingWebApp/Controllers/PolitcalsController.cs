using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using PTCVotingWebApp.Models;

namespace PTCVotingWebApp.Controllers
{
    public class PolitcalsController : Controller
    {
        private readonly voteShieldContext _context;

        public PolitcalsController(voteShieldContext context)
        {
            _context = context;
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

        // GET: Politcals
        public async Task<IActionResult> Index()
        {
            clearDups();

            Task t = Task.Run(() => {
                Task.Delay(5000).Wait();
                Console.WriteLine("Task ended delay...");
            });

            return View(await _context.Politcal.ToListAsync());
        }

        // GET: Politcals/Details/5
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var politcal = await _context.Politcal
                .FirstOrDefaultAsync(m => m.Politcal1 == id);
            if (politcal == null)
            {
                return NotFound();
            }

            return View(politcal);
        }

        // GET: Politcals/Create
        [Authorize(Roles = "Administrators")]
        public IActionResult Create()
        {
            return View();
        }

        // POST: Politcals/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> Create([Bind("Politcal1,FkId,PoliticalParty,Name,ImageUrl,Description")] Politcal politcal)
        {
            if (ModelState.IsValid)
            {
                _context.Add(politcal);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(politcal);
        }

        // GET: Politcals/Edit/5
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var politcal = await _context.Politcal.FindAsync(id);
            if (politcal == null)
            {
                return NotFound();
            }
            return View(politcal);
        }

        // POST: Politcals/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> Edit(int id, [Bind("Politcal1,FkId,PoliticalParty,Name,ImageUrl,Description")] Politcal politcal)
        {
            if (id != politcal.Politcal1)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(politcal);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!PolitcalExists(politcal.Politcal1))
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
            return View(politcal);
        }

        // GET: Politcals/Delete/5
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var politcal = await _context.Politcal
                .FirstOrDefaultAsync(m => m.Politcal1 == id);
            if (politcal == null)
            {
                return NotFound();
            }

            return View(politcal);
        }

        // POST: Politcals/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        [Authorize(Roles = "Administrators")]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var politcal = await _context.Politcal.FindAsync(id);
            _context.Politcal.Remove(politcal);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }
        string username = "ShhhImASecret";
        string password = "ShhhImABiggerSecret123@";
        private bool PolitcalExists(int id)
        {
            return _context.Politcal.Any(e => e.Politcal1 == id);
        }
    }
}
