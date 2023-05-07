namespace Magazin.Data.Services
{
    public interface ICartItemService
    {
        List<CartItem> getCartItems(User user);

        void addProduct(Product product, User user);

        void removeProduct(Product product, User user);

        void checkout(User user);

        decimal totalPrice(List<CartItem> listCartItems);
    }
}
