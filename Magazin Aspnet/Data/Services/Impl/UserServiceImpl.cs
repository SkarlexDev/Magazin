using System.Security.Cryptography;
using System.Text;

namespace Magazin.Data.Services.Impl
{
    public class UserServiceImpl : IUserService
    {
        private readonly ApplicationDbContext _context;
        private readonly IRole _role;

        public UserServiceImpl(ApplicationDbContext context, IRole role)
        {
            _context = context;
            _role = role;
        }

        public void addUser(User user)
        {
            using (SHA256 sha256Hash = SHA256.Create())
            {
                var pw = user.Password;
                byte[] bytes = Encoding.UTF8.GetBytes(pw);
                byte[] hash = sha256Hash.ComputeHash(bytes);
                user.Password = Convert.ToBase64String(hash);
            }
            if (string.IsNullOrEmpty(user.Username))
            {
                user.Username = null;
            }
            Role roleUser = _role.getRoleByName("USER");
            UserRole userRole = new UserRole
            {
                User = user,
                Role = roleUser
            };
            user.UserRoles.Add(userRole);
            _context.Users.Add(user);
            _context.SaveChanges();
        }

        public bool checkEmail(string email)
        {
            return _context.Users.Any(u => u.Email.Equals(email));
        }

        public User? getUserEmail(string email)
        {
            return _context.Users
                .Include(u => u.UserRoles)
                    .ThenInclude(ur => ur.Role)
                        .FirstOrDefault(u => u.Email.Equals(email) || u.Username.Equals(email));
        }

        public User? getUserId(long id)
        {
            return _context.Users.Find(id);
        }

        public void updateUser(User user)
        {
            _context.Update(user);
            _context.SaveChanges();
        }

    }
}
