namespace Magazin.Data.Services
{
    public interface IOrderService
    {
        List<Order> getOrdersByUser(User user);
        Order getOrderByOrderID(string orderID);
        Order getOrderId(long id);
    }
}
