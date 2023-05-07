namespace Magazin.Models
{
    [Table("orderitems")]
    public class OrderItem
    {
        [Key]
        [Column("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("productId")]
        public long ProductId { get; set; }

        [Column("quantity")]
        public int Quantity { get; set; }

        [Column("price")]
        public double Price { get; set; }

        [Column("order_id")]
        public long OrderId { get; set; }

        public virtual Order? Order { get; set; }

        public virtual Product? Product { get; set; }

        public OrderItem() { }
        public OrderItem(long productId, int quantity, double price, long orderId, Order order, Product product)
        {
            ProductId = productId;
            Quantity = quantity;
            Price = price;
            OrderId = orderId;
            Order = order;
            Product = product;
        }

    }
}
