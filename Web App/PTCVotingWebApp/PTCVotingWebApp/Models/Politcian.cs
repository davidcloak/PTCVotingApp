using System.ComponentModel.DataAnnotations;

namespace PTCVotingWebApp.Models
{
    public class Politcian
    {
        [Required]
        [MinLength(3, ErrorMessage = "Name has to 3 or more characters long.")]
        public string name { get; set; }
        [Required]
        [MinLength(3, ErrorMessage = "Party has to 3 or more characters long.")]
        public string party { get; set; }

        public string description { get; set; }

        [Required]
        [MinLength(3, ErrorMessage = "Party has to 3 or more characters long.")]
        public string title { get; set; }
        [Required]
        [MinLength(3, ErrorMessage = "State has to 3 or more characters long.")]
        public string state { get; set; }
        [Required]
        [MinLength(3, ErrorMessage = "City has to 3 or more characters long.")]
        public string city { get; set; }
    }
}
