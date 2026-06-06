/* References:
    - PageModel Class: https://learn.microsoft.com/en-us/dotnet/api/microsoft.aspnetcore.mvc.razorpages.pagemodel?view=aspnetcore-10.0
    - Action return types: https://learn.microsoft.com/en-us/aspnet/core/web-api/action-return-types?view=aspnetcore-10.0
 */

using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using AirbnbGuesthouseSite.Data;
using AirbnbGuesthouseSite.Models;

namespace AirbnbGuesthouseSite.Pages;

public class Contact : PageModel
{
    private readonly AirbnbGuesthouseSiteContext _context;
    public void OnGet() {}
    
    public Contact(AirbnbGuesthouseSiteContext context)
    {
        _context = context;
    }

    [BindProperty]
    public Enquiry Enquiry { get; set; } = new();

    [TempData]
    public string SuccessMessage { get; set; }
    
    public async Task<IActionResult> OnPostAsync()
    {
        if (!ModelState.IsValid)
        {
            return Page();
        }

        _context.Enquiries.Add(Enquiry);
        await _context.SaveChangesAsync();

        SuccessMessage = "Enquiry sent";
        return RedirectToPage();
    }
}