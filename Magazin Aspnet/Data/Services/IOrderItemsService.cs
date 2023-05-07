namespace Magazin.Data.Services
{
    public interface IOrderItemsService
    {
        List<OrderItem> getById(long orderId);
    }
}
