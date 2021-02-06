using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class Voting
    {
        public string Canidate { get; set; }
        public byte? FkVote { get; set; }

        public virtual Politcal FkVoteNavigation { get; set; }
    }
}
