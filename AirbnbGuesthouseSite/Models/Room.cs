using System.ComponentModel.DataAnnotations;

namespace AirbnbGuesthouseSite.Models;

public class Room
{
    [Key]
    public int Id { get; set; }
    
    [StringLength(50)]
    public string RoomName { get; set; }
    
    [StringLength(100)]
    public string RoomDescription { get; set; }
}