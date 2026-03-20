using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace AirbnbGuesthouseSite.Migrations
{
    /// <inheritdoc />
    public partial class EditEnquiries : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.AddColumn<string>(
                name: "Message",
                table: "Enquiries",
                type: "nvarchar(1000)",
                maxLength: 1000,
                nullable: false,
                defaultValue: "");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropColumn(
                name: "Message",
                table: "Enquiries");
        }
    }
}
