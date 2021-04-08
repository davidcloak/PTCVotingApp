using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Microsoft.EntityFrameworkCore;
using PTCVotingWebApp.Models;

namespace PTCVotingWebApp.Controllers
{
    public class VotingsController : Controller
    {
        private readonly voteShieldContext _context;

        public VotingsController(voteShieldContext context)
        {
            _context = context;
        }

        // GET: Votings
        public async Task<IActionResult> Index()
        {
            return View(await _context.Voting.ToListAsync());
        }

        // GET: Votings/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var voting = await _context.Voting
                .FirstOrDefaultAsync(m => m.Voting1 == id);
            if (voting == null)
            {
                return NotFound();
            }

            return View(voting);
        }

        // GET: Votings/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Votings/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Voting1,Canidate,FkRace,LocationId")] Voting voting)
        {
            if (ModelState.IsValid)
            {
                _context.Add(voting);
                await _context.SaveChangesAsync();
                return RedirectToAction(nameof(Index));
            }
            return View(voting);
        }

        // GET: Votings/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var voting = await _context.Voting.FindAsync(id);
            if (voting == null)
            {
                return NotFound();
            }
            return View(voting);
        }

        // POST: Votings/Edit/5
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Voting1,Canidate,FkRace,LocationId")] Voting voting)
        {
            if (id != voting.Voting1)
            {
                return NotFound();
            }

            if (ModelState.IsValid)
            {
                try
                {
                    _context.Update(voting);
                    await _context.SaveChangesAsync();
                }
                catch (DbUpdateConcurrencyException)
                {
                    if (!VotingExists(voting.Voting1))
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
            return View(voting);
        }

        // GET: Votings/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var voting = await _context.Voting
                .FirstOrDefaultAsync(m => m.Voting1 == id);
            if (voting == null)
            {
                return NotFound();
            }

            return View(voting);
        }

        // POST: Votings/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var voting = await _context.Voting.FindAsync(id);
            _context.Voting.Remove(voting);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool VotingExists(int id)
        {
            return _context.Voting.Any(e => e.Voting1 == id);
        }
    }
}
