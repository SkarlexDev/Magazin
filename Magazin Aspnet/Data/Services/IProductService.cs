namespace Magazin.Data.Services
{
    public interface IProductService
    {
        Product getById(long id);
        Product getByProductLink(string productLink);
        List<Product> getAll();
        List<Product> getAllByCategory(string category);
        void saveProduct(Product product);

        void updateProduct(Product product);
        void disableProduct(long id);
        void enableProduct(long id);
    }
}
