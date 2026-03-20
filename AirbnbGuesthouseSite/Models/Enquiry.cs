/* References:
- "Auto propert initilizer", retrieved from": https://csharp-evolution.com/6.0/auto-property-initializer
*/

using System.ComponentModel.DataAnnotations;

namespace AirbnbGuesthouseSite.Models;

public class Enquiry
{
    [Key]
    public int Id { get; set; }
    
    [Required, StringLength(75)]
    public string Name { get; set; }
    
    [Required, EmailAddress, StringLength(100)]
    public string Email { get; set; }
}