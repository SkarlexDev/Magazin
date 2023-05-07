namespace Magazin.Models
{
    [Table("Category")]
    [Index(nameof(Name),IsUnique = true)]
    public class Category
    {
        [Key]
        [Column("id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }
        [Column("name")]
        [Required]
        public string Name { get; set; }

        public Category() { }
        public Category(string name)
        {
            Name = name;
        }
    }
}

