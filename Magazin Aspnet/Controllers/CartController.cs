using Microsoft.AspNetCore.Mvc;

namespace Magazin.Controllers
{
    public class CartController : Controller
    {
        private readonly IUserService _userService;
        private readonly IProductService _productService;
        private readonly ICartItemService _cartItemService;

        public CartController(IUserService userService, IProductService productService, ICartItemService cartItemService)
        {
            _userService = userService;
            _productService = productService;
            _cartItemService = cartItemService;
        }
        public IActionResult Add()
        {
            long productId = long.Parse(Request.Query["productId"]);
            User user = _userService.getUserEmail(User.Identity.Name);
            if (user != null)
            {
                Product product = _productService.getById(productId);
                if (product != null)
                {
                    _cartItemService.addProduct(product, user);
                }

            }
            return RedirectToAction("Index", "Home");
        }

        public IActionResult Remove()
        {
            long productId = long.Parse(Request.Query["productId"]);
            User user = _userService.getUserEmail(User.Identity.Name);
            if (user != null)
            {
                Product product = _productService.getById(productId);
                if (product != null)
                {
                    int quantity = 1;
                    if (Request.Query != null && Request.Query.ContainsKey("q") && Request.Query["q"].Count > 0)
                    {
                        int.TryParse(Request.Query["q"], out quantity);
                    }

                    for (int i = 0; i <= quantity; i++)
                    {
                        _cartItemService.removeProduct(product, user);
                    }
                }

            }
            return RedirectToAction("Index", "Home");
        }     

        public IActionResult Cart()
        {
            User user = _userService.getUserEmail(User.Identity.Name);
            if (user == null)
            {
                return RedirectToAction("Index", "Home");

            }
            List<CartItem> cartItems = _cartItemService.getCartItems(user);
            decimal totalPrice = _cartItemService.totalPrice(cartItems);
            ViewData["TotalPrice"] = totalPrice;
            ViewData["CartItem"] = cartItems;
            return View();
        }

        public IActionResult DoCheckout()
        {
            User user = _userService.getUserEmail(User.Identity.Name);
            if (user!=null)
            {
                _cartItemService.checkout(user);
            }
            return RedirectToAction("Index", "Home");
        }
    }
}
