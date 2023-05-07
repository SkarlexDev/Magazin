namespace Magazin.Models
{
    [Table("orders")]
    public class Order
    {
        [Key]
        [Column("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("total_price")]
        public double TotalPrice { get; set; }

        public virtual List<OrderItem>? OrderItems { get; set; }

        [ForeignKey("UserId")]
        public virtual User? User { get; set; }

        [Column("order_id")]
        public string? OrderId { get; set; }

        [Column("user_fullName")]
        public string? UserFullName { get; set; }

        [Column("user_phone")]
        public string? UserPhone { get; set; }

        [Column("user_zip")]
        public string? UserZip { get; set; }

        [Column("user_city")]
        public string? UserCity { get; set; }

        [Column("user_state")]
        public string? UserState { get; set; }

        [Column("user_address")]
        public string? UserAddress { get; set; }

        [Column("user_email")]
        public string? UserEmail { get; set; }
        public Order(){}
        public Order(User user)
        {
            User = user;
        }

        public void SaveOrder(string userFullName, string userZip, string userAddress, string userState, string userPhone, string userCity, string userEmail)
        {
            OrderId = "ORD" + Id.ToString("D6");
            UserFullName = userFullName;
            UserZip = userZip;
            UserAddress = userAddress;
            UserState = userState;
            UserPhone = userPhone;
            UserCity = userCity;
            UserEmail = userEmail;
        }
    }
}
