using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using AirbnbGuesthouseSite.Data;
using AirbnbGuesthouseSite.Models;

namespace AirbnbGuesthouseSite.Pages.Room
{
    public class IndexModel : PageModel
    {
        private readonly AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext _context;

        public IndexModel(AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext context)
        {
            _context = context;
        }

        public IList<Models.Room> Room { get;set; } = default!;

        public async Task OnGetAsync()
        {
            Room = await _context.Rooms.ToListAsync();
        }
    }
}
