using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Models
{
    public class Results
    {
        string votes;
        string canidate;
        string race;

        public string Race { get => race; set => race = value; }
        public string Canidate { get => canidate; set => canidate = value; }
        public string Votes { get => votes; set => votes = value; }
    }
}
