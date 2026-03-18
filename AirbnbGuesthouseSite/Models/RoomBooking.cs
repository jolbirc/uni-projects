using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace AirbnbGuesthouseSite.Models;

public class RoomBooking
{
    [Key]
    public int ID
    {
        get; set;
    }

    [StringLength(30)]
    public string RoomName
    {
        get; set;
    }

    [StringLength(100)]
    public string RoomDesc
    {
        get; set; 
        
    }

    [StringLength(100)]
    [DataType(DataType.Currency)]
    [Column(TypeName = "Money")]
    public Nullable<decimal> PricePerNight
    {
        get; set;
    }

    [StringLength(250)]
    public string ImageDescription
    {
        get; set;
    }
    
    public byte[] ImageData
    {
        get; set;
    }
}