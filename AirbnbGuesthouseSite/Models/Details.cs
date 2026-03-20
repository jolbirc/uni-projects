/* References:
- "Working with Imags in an ASP.NET Web Pages (Razor) Site", retrieved from": https://learn.microsoft.com/en-us/aspnet/web-pages/overview/ui-layouts-and-themes/9-working-with-images 
*/

using System.ComponentModel.DataAnnotations;

namespace AirbnbGuesthouseSite.Models;

public class Details
{
    [Key]
    public int Id { get; set; }
    
    [StringLength(50)]
    public string PropertyName { get; set; }
    
    [StringLength(500)]
    public string PropertyDescription { get; set; }
}