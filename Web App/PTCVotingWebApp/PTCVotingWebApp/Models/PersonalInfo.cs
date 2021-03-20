using System;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class PersonalInfo
    {
        public string FirstN { get; set; }
        public string LastN { get; set; }
        public string Email { get; set; }
        public DateTime Birthday { get; set; }

    }
}
