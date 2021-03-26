namespace PTCVotingWebApp.Models
{
    public partial class RunnerModel
    {
        string name;
        string politicalParty;
        string description;

        public string Name { get => name; set => name = value; }
        public string PoliticalParty { get => politicalParty; set => politicalParty = value; }
        public string Description { get => description; set => description = value; }
    }
}
