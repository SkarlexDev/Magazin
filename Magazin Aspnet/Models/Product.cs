namespace Magazin.Models
{
    [Table("Products")]
    [Index(nameof(ProductLink), IsUnique = true)]
    public class Product
    {
        [Key]
        [Column("product_id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("productlink")]
        public string? ProductLink { get; set; }

        [Column("productName")]
        public string? ProductName { get; set; }

        [Column("description")]
        public string? Description { get; set; }

        [Column("price")]
        public double Price { get; set; }

        [Column("imageURL")]
        public string? ImageURL { get; set; }

        [Column("category")]
        public string? Category { get; set; }

        [Column("active")]
        public bool Active { get; set; }

        public Product() { }
    }
}
