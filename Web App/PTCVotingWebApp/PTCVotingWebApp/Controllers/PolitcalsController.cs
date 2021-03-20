using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using PTCVotingWebApp.Models;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Controllers
{
    public class PolitcalsController : Controller
    {
        private readonly voteShieldContext _context;

        public PolitcalsController(voteShieldContext context)
        {
            _context = context;
        }

        // GET: Politcals
        public async Task<IActionResult> Index()
        {
            return View(await _context.Politcal.ToListAsync());
        }

        // GET: Politcals/Details/5
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
        public IActionResult Create()
        {
            return View();
        }

        // POST: Politcals/Create
        // To protect from overposting attacks, enable the specific properties you want to bind to, for 
        // more details, see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Politcal1,FkId,PoliticalParty,Name")] Politcal politcal)
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
        public async Task<IActionResult> Edit(int id, [Bind("Politcal1,FkId,PoliticalParty,Name")] Politcal politcal)
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
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            var politcal = await _context.Politcal.FindAsync(id);
            _context.Politcal.Remove(politcal);
            await _context.SaveChangesAsync();
            return RedirectToAction(nameof(Index));
        }

        private bool PolitcalExists(int id)
        {
            return _context.Politcal.Any(e => e.Politcal1 == id);
        }
    }
}
