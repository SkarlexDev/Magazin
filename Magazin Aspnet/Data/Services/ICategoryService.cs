namespace Magazin.Data.Services
{
    public interface ICategoryService
    {
        List<Category> getAll();

        void saveCategory(string name);

        void deleteCategory(long id);

        Category getByName(string name);
    }
}
