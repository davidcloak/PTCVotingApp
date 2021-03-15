using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace PTCVotingWebApp.Models
{
    public class PoleHolderModel
    {
        List<PolesModel> holder;

        public List<PolesModel> Holder { get => holder; set => holder = value; }
    }
}
