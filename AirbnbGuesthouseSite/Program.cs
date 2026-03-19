/* REFERENCES
- "Scaffold Identity into an MVC project without existing authorization", retrieved from https://learn.microsoft.com/en-us/aspnet/core/security/authentication/scaffold-identity?view=aspnetcore-6.0&tabs=visual-studio#scaffold-identity-into-a-razor-project-with-authorization
- "Users and Roles Seeding in ASP.NEt Core Identity with Entity Framework Core", retrieved from https://medium.com/@roshanj100/users-and-roles-seeding-in-asp-net-core-identity-with-entity-framework-core-a-step-by-step-guide-28e6f76a18db
*/

using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using AirbnbGuesthouseSite.Data;
using Microsoft.AspNetCore.Identity;

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

app.UseHttpsRedirection();
app.UseRouting();
app.UseAuthentication();
app.UseAuthorization();
app.MapRazorPages();
app.MapStaticAssets();

using (var scope = app.Services.CreateScope())
{
    var userManager = scope.ServiceProvider.GetRequiredService<UserManager<IdentityUser>>();

    var email = "admin@guesthouse.local";
    var password = "ChangeMe123!";

    var existingUser = await userManager.FindByEmailAsync(email);

    if (existingUser == null)
    {
        var user = new IdentityUser
        {
            UserName = email,
            Email = email,
            EmailConfirmed = true
        };

        var result = await userManager.CreateAsync(user, password);

        if (!result.Succeeded)
        {
            foreach (var error in result.Errors)
            {
                Console.WriteLine($"SEED ERROR: {error.Code} - {error.Description}");
            }
        }
        else
        {
            Console.WriteLine("SEED OK: Admin user created.");
        }
    }
    else
    {
        Console.WriteLine("SEED INFO: Admin user already exists.");
    }
}

app.Run();