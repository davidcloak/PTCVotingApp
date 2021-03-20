using System.Collections.Generic;

namespace PTCVotingWebApp.Models
{
    public class PoleHolderModel
    {
        List<PolesModel> holder;

        public List<PolesModel> Holder { get => holder; set => holder = value; }
    }
}
