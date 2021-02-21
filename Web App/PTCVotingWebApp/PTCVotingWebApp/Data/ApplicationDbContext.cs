using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using PTCVotingWebApp.Models;

namespace PTCVotingWebApp.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options)
            : base(options)
        {
        }
        public DbSet<PTCVotingWebApp.Models.Race> Race { get; set; }
        public DbSet<PTCVotingWebApp.Models.Politcal> Politcal { get; set; }
    }
}
