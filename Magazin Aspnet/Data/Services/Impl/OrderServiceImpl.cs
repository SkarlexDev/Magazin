namespace Magazin.Data.Services.Impl
{
    public class OrderServiceImpl : IOrderService
    {
        private readonly ApplicationDbContext _context;

        public OrderServiceImpl(ApplicationDbContext context)
        {
            _context = context;
        }

        public List<Order> getOrdersByUser(User user)
        {
            return _context.Orders.Where(o => o.User == user).ToList();
        }

        public Order getOrderByOrderID(string orderId)
        {
            return _context.Orders
            .Include(o => o.User)
            .Include(o => o.OrderItems)
                .ThenInclude(oi => oi.Product)
                    .FirstOrDefault(o => o.OrderId == orderId);
        }

        public Order getOrderId(long id)
        {
            return _context.Orders.FirstOrDefault(o => o.Id == id);
        }


    }
}
