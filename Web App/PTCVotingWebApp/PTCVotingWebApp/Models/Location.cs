using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class Location
    {
        [Key]
        public int LocationId { get; set; }
        public string State { get; set; }
        public string City { get; set; }
    }
}
