/* REFERENCES
- "Scaffold Identity into an MVC project without existing authorization", retrieved from https://learn.microsoft.com/en-us/aspnet/core/security/authentication/scaffold-identity?view=aspnetcore-6.0&tabs=visual-studio#scaffold-identity-into-a-razor-project-with-authorization
*/

using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using AirbnbGuesthouseSite.Data;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Identity;
using AirbnbGuesthouseSite.Areas.Identity.Data;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddDefaultIdentity<IdentityUser>(options  => options.SignIn.RequireConfirmedAccount = false).AddEntityFrameworkStores<AirbnbGuesthouseSiteContext>();
builder.Services.AddRazorPages(options =>
{
    options.Conventions.AuthorizeFolder("/Room");
});
builder.Services.AddDbContext<AirbnbGuesthouseSiteContext>(options =>
    options.UseSqlServer(builder.Configuration.GetConnectionString("AirbnbGuesthouseSiteContext") ?? throw new InvalidOperationException("Connection string 'AirbnbGuesthouseSiteContext' not found.")));
builder.Services.AddDatabaseDeveloperPageExceptionFilter();
builder.Services.ConfigureApplicationCookie(options =>
{
    options.LoginPath = "/Identity/Account/Login";
    options.AccessDeniedPath = "/Identity/Account/AccessDenied";
});

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

using (var scope = app.Services.CreateScope())
{
    var services = scope.ServiceProvider;
    var context = services.GetRequiredService<AirbnbGuesthouseSiteContext>();
    context.Database.EnsureCreated();
    
}

app.UseHttpsRedirection();

app.UseRouting();

app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();

app.MapStaticAssets();
app.MapRazorPages()
    .WithStaticAssets();

app.Run();