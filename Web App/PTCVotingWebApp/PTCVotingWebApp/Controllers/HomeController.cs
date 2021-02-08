using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using PTCVotingWebApp.Models;

namespace PTCVotingWebApp.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        public IActionResult InfoForm()
        {
            return View();
        }

        public IActionResult VoteForm()
        {
            List<string> listOfCanadits = new List<string>();
            listOfCanadits.Add("This will be a list of canadits");
            listOfCanadits.Add("Joe");
            listOfCanadits.Add("Bob");
            listOfCanadits.Add("etc.");

            ViewBag.CandidateList = listOfCanadits;
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}
