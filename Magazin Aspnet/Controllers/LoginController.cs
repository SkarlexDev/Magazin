using Microsoft.AspNetCore.Mvc;
using System.Security.Cryptography;
using System.Text;
using System.Security.Claims;
using Microsoft.AspNetCore.Authentication;
using Microsoft.AspNetCore.Authentication.Cookies;
using Microsoft.AspNetCore.Authorization;

namespace Magazin.Controllers
{
    public class LoginController : Controller
    {
        private readonly IUserService _userService;

        public LoginController(IUserService userService)
        {
            _userService = userService;
        }

        [HttpGet]
        public IActionResult Login(string error = null)
        {
            // Redirect to home page if user is already authenticated
            if (User.Identity.IsAuthenticated)
            {
                return RedirectToAction(nameof(HomeController.Index), "Home");
            }

            ViewBag.Error = error;

            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Login(string username, string password, string error = null)
        {
            if (string.IsNullOrEmpty(username) || string.IsNullOrEmpty(password))
            {
                return RedirectToAction(nameof(Login), "Login", new { error = "invalid" });
            }

            var user = _userService.getUserEmail(username);

            if (user != null && VerifyPassword(user.Password, password))
            {
                // Authentication successful, create claims and authentication cookie
                var claims = new List<Claim> {
                    new Claim(ClaimTypes.Name, user.Email)
                };

                ICollection<UserRole>  uroles = user.UserRoles;
                if (uroles != null)
                {
                    foreach (UserRole ur in uroles)
                    {
                        claims.Add(new Claim(ClaimTypes.Role, ur.Role.Name));
                    }
                }
                var identity = new ClaimsIdentity(claims, CookieAuthenticationDefaults.AuthenticationScheme);
                var principal = new ClaimsPrincipal(identity);
                await HttpContext.SignInAsync(CookieAuthenticationDefaults.AuthenticationScheme, principal);

                return RedirectToAction(nameof(HomeController.Index), "Home");
            }
            else
            {
                error = "username or password";
                return RedirectToAction(nameof(Login), "Login", new { error = error });
            }
        }

        [HttpPost]
        public async Task<IActionResult> Logout()
        {
            await HttpContext.SignOutAsync(CookieAuthenticationDefaults.AuthenticationScheme);

            TempData["SuccessMessage"] = "You have successfully logged out.";
            return RedirectToAction(nameof(Login), "Login");
        }

        private bool VerifyPassword(string hashedPassword, string password)
        {
            using (SHA256 sha256Hash = SHA256.Create())
            {
                byte[] bytes = Encoding.UTF8.GetBytes(password);
                byte[] hash = sha256Hash.ComputeHash(bytes);
                string hashedInput = Convert.ToBase64String(hash);
                return hashedInput == hashedPassword;
            }
        }
    }
}

