using System.ComponentModel.DataAnnotations;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace PTCVotingWebApp.Models
{
    public partial class Race
    {
        public string Race1 { get; set; }
        public int FkPolitcal { get; set; }
        [Key]
        public int Pk { get; set; }
        public int? LocationId { get; set; }
    }
}
