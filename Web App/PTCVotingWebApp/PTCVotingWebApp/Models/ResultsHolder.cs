using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Models
{
    public class ResultsHolder
    {
        List<Results> results;

        public List<Results> Results { get => results; set => results = value; }
    }
}
