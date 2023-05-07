namespace Magazin.Data.Services.Impl
{
    public class OrderItemsServiceImpl : IOrderItemsService
    {
        private readonly ApplicationDbContext _context;

        public OrderItemsServiceImpl(ApplicationDbContext context)
        {
            _context = context;
        }
        public List<OrderItem> getById(long orderId)
        {
            return _context.OrderItems.Where(o => o.OrderId == orderId).ToList();
        }
    }
}
