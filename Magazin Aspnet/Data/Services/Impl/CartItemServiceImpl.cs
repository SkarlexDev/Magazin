namespace Magazin.Data.Services.Impl
{
    public class CartItemServiceImpl : ICartItemService
    {
        private readonly ApplicationDbContext _context;

        public CartItemServiceImpl(ApplicationDbContext context)
        {
            _context = context;
        }
        public void addProduct(Product product, User user)
        {
            CartItem cartItem = _context.CartItems.FirstOrDefault(c => c.User == user && c.Product == product);

            if (cartItem == null)
            {
                cartItem = new CartItem();
                cartItem.Product = product;
                cartItem.User = user;
            }

            List<CartItem> listCartItems = getCartItems(user);

            bool singleProduct = true;

            foreach (CartItem lci in listCartItems)
            {
                if (lci.Product.Id == cartItem.Product.Id)
                {
                    cartItem.Quantity = cartItem.Quantity + 1;
                    singleProduct = false;
                }
            }

            if (singleProduct)
            {
                cartItem.Quantity = 1;
            }

            _context.CartItems.Update(cartItem);
            _context.SaveChanges();
        }

        public void checkout(User user)
        {
            List<CartItem> listCartItems = getCartItems(user);

            if (listCartItems.Count != 0)
            {
                Order order = new Order(user);
                _context.Orders.Add(order);
                _context.SaveChanges();
                double totalPrice = 0;

                foreach (CartItem lci in listCartItems)
                {
                    double price = lci.Product.Price * lci.Quantity;
                    OrderItem oi = new OrderItem(lci.Id, lci.Quantity, price, order.Id, order, lci.Product);
                    totalPrice = totalPrice + price;
                    _context.OrderItems.Add(oi);
                }

                order.TotalPrice = totalPrice;
                order.SaveOrder(user.FullName, user.Zip, user.Address, user.State, user.Phone, user.City, user.Email);
                _context.Orders.Update(order);
                _context.CartItems.RemoveRange(listCartItems);
                _context.SaveChanges();
            }
        }

        public List<CartItem> getCartItems(User user)
        {
            return _context.CartItems.Include(c => c.Product).Where(c => c.User == user).ToList();
        }

        public void removeProduct(Product product, User user)
        {
            CartItem cartItem = _context.CartItems.FirstOrDefault(c => c.User == user && c.Product == product);
            List<CartItem> listCartItems = getCartItems(user);
            if (listCartItems != null && cartItem != null)
            {
                foreach (CartItem lci in listCartItems)
                {
                    if (lci.Product.Id == cartItem.Product.Id)
                    {
                        if (cartItem.Quantity == 1)
                        {
                            _context.CartItems.Remove(cartItem);
                        }
                        else
                        {
                            cartItem.Quantity = cartItem.Quantity - 1;
                            _context.CartItems.Update(cartItem);
                        }
                        _context.SaveChanges();
                        return;
                    }
                }
            }
            
        }

        public decimal totalPrice(List<CartItem> listCartItems)
        {
            decimal totalPrice = 0;

            if (listCartItems != null)
            {
                foreach (CartItem lci in listCartItems)
                {
                    string price = Convert.ToString(lci.Product.Price * lci.Quantity);
                    totalPrice += Convert.ToDecimal(price);
                }
            }

            return totalPrice;
        }
    }
}
