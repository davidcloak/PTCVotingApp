using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class HelpRequests
    {
        [Key]
        public int ProblemId { get; set; }
        public string Name { get; set; }
        public string Problem { get; set; }
        public string Email { get; set; }
        public bool Solve { get; set; }
    }
}
