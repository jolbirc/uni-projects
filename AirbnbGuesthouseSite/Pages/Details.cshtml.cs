using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;
using AirbnbGuesthouseSite.Data;
using AirbnbGuesthouseSite.Models;

namespace AirbnbGuesthouseSite.Pages;

public class Details : PageModel
{
    private readonly AirbnbGuesthouseSiteContext _context;

    public Details(AirbnbGuesthouseSiteContext context)
    {
        _context = context;
    }

    public IList<Models.Room> Rooms { get; set; } = new List<Models.Room>();

    public async Task OnGetAsync()
    {
        Rooms = await _context.Rooms.ToListAsync();
    }
}