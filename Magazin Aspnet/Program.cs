using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Routing.Constraints;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container.
builder.Services.AddControllersWithViews();
builder.Services.AddScoped<IUserService, UserServiceImpl>();
builder.Services.AddScoped<IRole, RoleImpl>();
builder.Services.AddScoped<IProductService, ProductServiceImpl>();
builder.Services.AddScoped<IOrderService, OrderServiceImpl>();
builder.Services.AddScoped<IOrderItemsService, OrderItemsServiceImpl>();
builder.Services.AddScoped<ICategoryService, CategoryServiceImpl>();
builder.Services.AddScoped<ICartItemService, CartItemServiceImpl>();

// connecting to db
var connectionString = builder.Configuration.GetConnectionString("DefaultConnection");

builder.Services.AddDbContext<ApplicationDbContext>(options =>
{
    options.UseMySql(connectionString, ServerVersion.AutoDetect(connectionString));
});

// Add session
builder.Services.AddSession(options =>
{
    options.IdleTimeout = TimeSpan.FromMinutes(30);
    options.Cookie.HttpOnly = true;
    options.Cookie.IsEssential = true;
});

builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
    .AddCookie(options =>
    {
        options.Cookie.HttpOnly = true;
        options.ExpireTimeSpan = TimeSpan.FromMinutes(30);
        options.LoginPath = "/Login/Login";
        options.LogoutPath = "/Login/Logout";
    });

var app = builder.Build();

// Configure the HTTP request pipeline.
if (!app.Environment.IsDevelopment())
{
    app.UseExceptionHandler("/Home/Error");
    // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
    app.UseHsts();
}

app.UseHttpsRedirection();
app.UseStaticFiles();

app.UseRouting();

// Use session
app.UseSession();

app.UseAuthorization();

app.UseAuthentication();

app.MapControllerRoute(
    name: "login",
    pattern: "login",
    defaults: new { controller = "Login", action = "Login" });

app.MapControllerRoute(
    name: "register",
    pattern: "register",
    defaults: new { controller = "Register", action = "Register" });

app.MapControllerRoute(
    name: "check_email",
    pattern: "check_email/{email}",
    defaults: new { controller = "Register", action = "CheckEmail" },
    constraints: new { httpMethod = new HttpMethodRouteConstraint("POST") });

app.MapControllerRoute(
    name: "shop",
    pattern: "Home/Shop/{category?}",
    defaults: new { controller = "Home", action = "Index" });

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.MapControllerRoute(
    name: "product",
    pattern: "Home/Product",
    defaults: new { controller = "Home", action = "Product" });

app.MapControllerRoute(
    name: "profile",
    pattern: "profile",
    defaults: new { controller = "User", action = "Profile" });

app.MapControllerRoute(
    name: "order_details",
    pattern: "User/OrderDetail",
    defaults: new { controller = "User", action = "OrderDetail" });

app.MapControllerRoute(
    name: "cart_add",
    pattern: "cart/add",
    defaults: new { controller = "Cart", action = "Add" }
);

app.MapControllerRoute(
    name: "cart",
    pattern: "cart/",
    defaults: new { controller = "Cart", action = "Cart" }
);

app.MapControllerRoute(
    name: "checkout",
    pattern: "cart/checkout",
    defaults: new { controller = "Cart", action = "DoCheckout" }
);

app.MapControllerRoute(
    name: "admin",
    pattern: "admin",
    defaults: new { controller = "Admin", action = "Index" }
);

app.MapControllerRoute(
    name: "category_del",
    pattern: "category/delete",
    defaults: new { controller = "Admin", action = "DeleteCategory" }
);

app.MapControllerRoute(
    name: "category_new",
    pattern: "category/new",
    defaults: new { controller = "Admin", action = "NewCategory" },
    constraints: new { httpMethod = new HttpMethodRouteConstraint("POST") }
);

app.MapControllerRoute(
    name: "product_disable",
    pattern: "product/disable",
    defaults: new { controller = "Admin", action = "ProductDisable" }
);

app.MapControllerRoute(
    name: "product_enable",
    pattern: "product/enable",
    defaults: new { controller = "Admin", action = "ProductEnable" }
);

app.MapControllerRoute(
    name: "product_new",
    pattern: "product/new",
    defaults: new { controller = "Admin", action = "Product" }
);

app.MapControllerRoute(
    name: "product_edit",
    pattern: "product/edit",
    defaults: new { controller = "Admin", action = "ProductEdit" }
);


app.Run();
