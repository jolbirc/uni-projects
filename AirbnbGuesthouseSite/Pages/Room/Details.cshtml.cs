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
    public class DetailsModel : PageModel
    {
        private readonly AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext _context;

        public DetailsModel(AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext context)
        {
            _context = context;
        }

        public Models.Room Room { get; set; } = default!;

        public async Task<IActionResult> OnGetAsync(int? id)
        {
            if (id == null)
            {
                return NotFound();
            }

            var room = await _context.Rooms.FirstOrDefaultAsync(m => m.Id == id);

            if (room is not null)
            {
                Room = room;

                return Page();
            }

            return NotFound();
        }
    }
}
