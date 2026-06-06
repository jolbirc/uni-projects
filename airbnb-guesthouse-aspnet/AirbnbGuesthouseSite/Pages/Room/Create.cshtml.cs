using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.AspNetCore.Mvc.Rendering;
using AirbnbGuesthouseSite.Data;
using AirbnbGuesthouseSite.Models;

namespace AirbnbGuesthouseSite.Pages.Room
{
    public class CreateModel : PageModel
    {
        private readonly AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext _context;

        public CreateModel(AirbnbGuesthouseSite.Data.AirbnbGuesthouseSiteContext context)
        {
            _context = context;
        }

        public IActionResult OnGet()
        {
            return Page();
        }

        [BindProperty]
        public Models.Room Room { get; set; } = default!;

        // For more information, see https://aka.ms/RazorPagesCRUD.
        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }

            _context.Rooms.Add(Room);
            await _context.SaveChangesAsync();

            return RedirectToPage("./Index");
        }
    }
}
