using Microsoft.AspNetCore.Mvc;

namespace Magazin.Controllers
{
    public class RegisterController : Controller
    {
        private readonly IUserService _userService;
        public RegisterController(IUserService userService)
        {
            _userService = userService;
        }
        [HttpGet]
        public IActionResult Register()
        {
            return View(new User());
        }

        [HttpPost]
        public IActionResult CheckEmail(string email)
        {
            bool emailExists = _userService.checkEmail(email);

            if (emailExists)
            {
                return Json(new { success = false, message = "Email already exists" });
            }
            else
            {
                return Json(new { success = true, message = "Email does not exist" });
            }
        }

        [HttpPost]
        public IActionResult RegisterUser(User user)
        {
            if (ModelState.IsValid)
            {
                _userService.addUser(user);
                return RedirectToAction("Index", "Home");
            }

            return View(user);
        }

    }
}
