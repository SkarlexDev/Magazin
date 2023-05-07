namespace Magazin.Data.Services.Impl
{
    public class RoleImpl : IRole
    {
        private readonly ApplicationDbContext _context;

        public RoleImpl(ApplicationDbContext context)
        {
            _context = context;

        }
        public Role getRoleByName(string name)
        {
            return _context.Roles.FirstOrDefault(r => r.Name.Equals(name));
        }
    }
}
