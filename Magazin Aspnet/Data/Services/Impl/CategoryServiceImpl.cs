namespace Magazin.Data.Services.Impl
{
    public class CategoryServiceImpl : ICategoryService
    {
        private readonly ApplicationDbContext _context;

        public CategoryServiceImpl(ApplicationDbContext context)
        {
            _context = context;
        }

        public void deleteCategory(long id)
        {
            var result = _context.Categorys.FirstOrDefault(c => c.Id == id);
            _context.Categorys.Remove(result);
            _context.SaveChanges();
        }

        public List<Category> getAll()
        {
            return _context.Categorys.ToList();
        }

        public Category getByName(string name)
        {
            return _context.Categorys.FirstOrDefault(c => c.Name.Equals(name));
        }

        public void saveCategory(string name)
        {
            _context.Categorys.Add(new Category(name));
            _context.SaveChanges();
        }
    }
}
