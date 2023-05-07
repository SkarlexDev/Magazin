namespace Magazin.Data.Services.Impl
{
    public class ProductServiceImpl : IProductService
    {
        private readonly ApplicationDbContext _context;
        public ProductServiceImpl(ApplicationDbContext context)
        {
            _context = context;
        }
        public void disableProduct(long id)
        {
            var product = _context.Products.FirstOrDefault(p => p.Id == id);
            if (product != null)
            {
                product.Active = false;
                _context.SaveChanges();
            }
        }

        public void enableProduct(long id)
        {
            var product = _context.Products.FirstOrDefault(p => p.Id == id);
            if (product != null)
            {
                product.Active = true;
                _context.SaveChanges();
            }
        }

        public List<Product> getAll()
        {
            return _context.Products.ToList();
        }

        public List<Product> getAllByCategory(string category)
        {
            return _context.Products.Where(p => p.Category.Equals(category)).ToList();
        }

        public Product getById(long id)
        {
            return _context.Products.FirstOrDefault(p => p.Id == id);
        }

        public Product getByProductLink(string productLink)
        {
            return _context.Products.FirstOrDefault(p => p.ProductLink.Equals(productLink));
        }

        public void saveProduct(Product product)
        {
            product.Active = true;
            _context.Products.Add(product);
            _context.SaveChanges();
        }

        public void updateProduct(Product product)
        {
            _context.Update(product);
            _context.SaveChanges();
        }
    }
}
