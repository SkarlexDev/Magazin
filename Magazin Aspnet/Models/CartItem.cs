namespace Magazin.Models
{
    [Table("cart_items")]
    public class CartItem
    {
        [Key]
        [Column("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }
        [Column("quantity")]
        public int Quantity { get; set; }

        [Column("product_id")]
        public long ProductId { get; set; }

        [Column("user_id")]
        public long UserId { get; set; }

        public virtual Product? Product { get; set; }

        public virtual User? User { get; set; }

        public CartItem() { }
    }
}
