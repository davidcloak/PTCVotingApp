using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Models
{
    public partial class RunnerModel
    {
        string name;
        string politicalParty;

        public string Name { get => name; set => name = value; }
        public string PoliticalParty { get => politicalParty; set => politicalParty = value; }
    }
}
