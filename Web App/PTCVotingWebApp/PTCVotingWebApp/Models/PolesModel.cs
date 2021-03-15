using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Models
{
    public class PolesModel
    {
       List<RunnerModel> runners;
        string state;
        string city;
        string race;

        public string Race { get => race; set => race = value; }
        public string City { get => city; set => city = value; }
        public string State { get => state; set => state = value; }
        public List<RunnerModel> Runners { get => runners; set => runners = value; }
    }
}
