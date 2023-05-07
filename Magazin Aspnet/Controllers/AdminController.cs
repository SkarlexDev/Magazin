using Microsoft.AspNetCore.Mvc;

namespace Magazin.Controllers
{
    public class AdminController : Controller
    {
        private readonly IProductService _productService;
        private readonly ICategoryService _categoryService;
        private readonly IUserService _userService;

        public AdminController(IProductService productService, ICategoryService categoryService, IUserService userService)
        {
            _productService = productService;
            _categoryService = categoryService;
            _userService = userService;
        }
        public IActionResult Index()
        {

            if (!User.Identity.IsAuthenticated || !(User.IsInRole("CREATOR") || User.IsInRole("ADMIN")))
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }
            List<Product> products = _productService.getAll().ToList();
            List<Category> categories = _categoryService.getAll();

            ViewData["Products"] = products;
            ViewData["Categories"] = categories;
            return View();
        }

        public IActionResult DeleteCategory()
        {
            if (!User.Identity.IsAuthenticated || !(User.IsInRole("CREATOR") || User.IsInRole("ADMIN")))
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }

            if (!int.TryParse(Request.Query["catId"], out int catId))
            {
                return BadRequest();
            }
            _categoryService.deleteCategory(catId);
            return RedirectToAction("Index");
        }

        [HttpPost]
        public IActionResult NewCategory()
        {
            string categoryName = Request.Query["catName"];
            if (_categoryService.getByName(categoryName) != null)
            {
                return BadRequest();
            }
            _categoryService.saveCategory(categoryName);
            return Ok();
        }

        public IActionResult ProductDisable()
        {
            int productId = int.Parse(Request.Query["productId"]);
            _productService.disableProduct(productId);
            return RedirectToAction("Index");
        }

        public IActionResult ProductEnable()
        {
            int productId = int.Parse(Request.Query["productId"]);
            _productService.enableProduct(productId);
            return RedirectToAction("Index");
        }

        public IActionResult Product()
        {

            if (!User.Identity.IsAuthenticated || !(User.IsInRole("CREATOR") || User.IsInRole("ADMIN")))
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }
            List<Category> categories = _categoryService.getAll();
            ViewData["Categories"] = categories;
            ViewData["IsEditing"] = false;
            return View();
        }

        [HttpPost]
        public IActionResult AddProduct(Product productInfo)
        {
            productInfo.ProductLink = productInfo.ProductName
                   .Replace(" ", "_")
                   .Replace("-", "")
                   .Replace(",", "")
                   .Replace("/", ".");
            ViewData["IsEditing"] = false;

            if (_productService.getByProductLink(productInfo.ProductLink) == null)
            {
                _productService.saveProduct(productInfo);
                return RedirectToAction("Index", "Admin");
            }
            else
            {
                ViewData["Categories"] = _categoryService.getAll();
                ViewData["error"] = "true";
                return View("Product", productInfo);
            }           
            
        }

        public IActionResult ProductEdit()
        {
            if (!User.Identity.IsAuthenticated || !(User.IsInRole("CREATOR") || User.IsInRole("ADMIN")))
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }
            int productId = int.Parse(Request.Query["productId"]);
            Product product = _productService.getById(productId);
            List<Category> categories = _categoryService.getAll();
            ViewData["Categories"] = categories;
            ViewData["IsEditing"] = true;
            return View("Product", product);
        }


        [HttpPost]
        public IActionResult EditProduct(Product productInfo)
        {
            try
            {
                int productId = int.Parse(Request.Form["productId"]);
                Product product = _productService.getById(productId);
                product.ProductLink = productInfo.ProductName
                      .Replace(" ", "_")
                      .Replace("-", "")
                      .Replace(",", "")
                      .Replace("/", ".");
                product.ProductName = productInfo.ProductName;
                product.Description = productInfo.Description;
                product.Price = productInfo.Price;
                product.ImageURL = productInfo.ImageURL;
                product.Category = productInfo.Category;
                _productService.updateProduct(product);
                return RedirectToAction("Index", "Admin");

             } catch (Exception ex){
                ViewData["Categories"] = _categoryService.getAll();
                ViewData["error"] = "true";
                ViewData["IsEditing"] = true;
                return View("Product", productInfo);
            }
        }

    }
}
