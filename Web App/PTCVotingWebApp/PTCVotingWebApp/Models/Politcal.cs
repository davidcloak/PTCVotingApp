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
        public string FkId { get; set; }
        public string PoliticalParty { get; set; }
        public string Name { get; set; }
    }
}
