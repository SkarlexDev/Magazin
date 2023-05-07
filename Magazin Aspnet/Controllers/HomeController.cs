using Magazin.Models;
using Microsoft.AspNetCore.Mvc;
using System.Diagnostics;

namespace Magazin.Controllers
{
    public class HomeController : Controller
    {

        private readonly IProductService _productService;
        private readonly ICategoryService _categoryService;
        private readonly IUserService _userService;

        public HomeController(IProductService productService, ICategoryService categoryService, IUserService userService)
        {
            _productService = productService;
            _categoryService = categoryService;
            _userService = userService;
        }

        public IActionResult Index()
        {
            string category = Request.Query["category"];

            List<Product> products = _productService.getAll().Where(p => p.Active).ToList();
            List<Category> categories = _categoryService.getAll();

            if (!string.IsNullOrEmpty(category))
            {
                products = products.Where(p => p.Category.ToLower() == category.ToLower()).ToList();
            }

            ViewData["Products"] = products;
            ViewData["Categories"] = categories;

            User user = _userService.getUserEmail(User.Identity.Name);

            if (user != null)
            {
                ViewData["User"] = user;
            }

            return View();
        }

        public IActionResult Product()
        {
            string category = Request.Query["category"];
            string productLink = Request.Query["product"];

            Product product = _productService.getByProductLink(productLink);
            string html = product.Description.Replace("\r\n", "<br>");
            product.Description = html;
            ViewData["Product"] = product;
            return View();
        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}