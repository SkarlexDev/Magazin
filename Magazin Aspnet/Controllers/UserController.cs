using Microsoft.AspNetCore.Mvc;

namespace Magazin.Controllers
{
    public class UserController : Controller
    {
        private readonly IUserService _userService;
        private IOrderService _orderService;

        public UserController(IUserService userService, IOrderService orderService)
        {
            _userService = userService;
            _orderService = orderService;
        }
        public IActionResult Profile()
        {
            User user = _userService.getUserEmail(User.Identity.Name);
            if (user == null)
            {
                return RedirectToAction("Index", "Home");

            }
            List<Order> orders = _orderService.getOrdersByUser(user);
            ViewData["UserData"] = user;
            ViewData["Orders"] = orders;

            return View();
        }

        [HttpPost]
        public IActionResult UpdateProfile(User userData)
        {
            User user = _userService.getUserEmail(User.Identity.Name);

            if (user != null)
            {
                user.FullName = userData.FullName;
                user.Phone = userData.Phone;
                user.Address = userData.Address;
                user.City = userData.City;
                user.State = userData.State;
                user.Zip = userData.Zip;
                _userService.updateUser(user);

                TempData["SuccessMessage"] = "Data updated !";
            }

            return RedirectToAction("Profile");
        }


        public IActionResult OrderDetail()
        {
            if (!User.Identity.IsAuthenticated)
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }

            string orderId = Request.Query["orderId"];
            if (orderId != null)
            {
                Order od = _orderService.getOrderByOrderID(orderId);
                if (od != null)
                {
                    User user = _userService.getUserEmail(User.Identity.Name);
                    if (user!=null && (od.User.Id == user.Id || (User.IsInRole("CREATOR") || User.IsInRole("ADMIN"))))
                    {
                        ViewData["Order"] = od;
                        return View();
                    }
                   
                }
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }
            return RedirectToAction(nameof(HomeController.Index), "Home");
        }

    }
}
