using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class Politcal
    {
        [Key]
        public int Politcal1 { get; set; }
        [Required]
        public string FkId { get; set; }
        [Required]
        [MinLength(3, ErrorMessage = "Party has to 3 or more characters long.")]
        public string PoliticalParty { get; set; }
        [Required]
        [MinLength(3, ErrorMessage = "Name has to 3 or more characters long.")]
        public string Name { get; set; }
        public string ImageUrl { get; set; }
        public string Description { get; set; }
    }
}
