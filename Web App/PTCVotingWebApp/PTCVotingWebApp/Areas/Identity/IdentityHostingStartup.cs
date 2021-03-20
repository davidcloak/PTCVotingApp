using Microsoft.AspNetCore.Hosting;

[assembly: HostingStartup(typeof(PTCVotingWebApp.Areas.Identity.IdentityHostingStartup))]
namespace PTCVotingWebApp.Areas.Identity
{
    public class IdentityHostingStartup : IHostingStartup
    {
        public void Configure(IWebHostBuilder builder)
        {
            builder.ConfigureServices((context, services) =>
            {
            });
        }
    }
}