using System.Collections.Generic;

namespace PTCVotingWebApp.Models
{
    public class PoliticianHolder
    {
        private static List<Politcian> politician = new List<Politcian>();
        public static IEnumerable<Politcian> ListPoliticians
        {
            get { return politician; }
        }
        public static void AddPolitician(Politcian newPolitician)
        {
            politician.Add(newPolitician);
        }

        public static void ClearPoliticians()
        {
            politician = new List<Politcian>();
        }

        public static void UpdatePoliticians(string oldName, Politcian newPolitician)
        {
            List<Politcian> updatePoliticianList = new List<Politcian>();

            foreach (var pol in politician)
            {
                if (pol.name.Equals(oldName))
                {
                    updatePoliticianList.Add(newPolitician);
                }
                else
                {
                    updatePoliticianList.Add(pol);
                }
            }

            politician = new List<Politcian>();
            foreach (var pol in updatePoliticianList)
            {
                politician.Add(pol);
            }
        }
    }
}
