using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using AirbnbGuesthouseSite.Models;

namespace AirbnbGuesthouseSite.Data
{
    public class AirbnbGuesthouseSiteContext : IdentityDbContext
    {
        public AirbnbGuesthouseSiteContext (DbContextOptions<AirbnbGuesthouseSiteContext> options)
            : base(options)
        {
        }

        public DbSet<Room> Rooms { get; set; } = default!;
    }
}
