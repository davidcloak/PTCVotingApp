using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class Voting
    {
        [Key]
        public int Voting1 { get; set; }
        public string Canidate { get; set; }
        public string FkRace { get; set; }
        public int? LocationId { get; set; }
    }
}
