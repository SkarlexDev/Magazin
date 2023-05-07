namespace Magazin.Models
{
    [Table("Users")]
    [Index(nameof(Email), IsUnique = true)]
    [Index(nameof(Username), IsUnique = true)]
    public class User
    {
        [Key]
        [Column("user_id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [Column("fullName")]
        public string? FullName { get; set; }

        [Column("phone")]
        public string? Phone { get; set; }

        [Column("address")]
        public string? Address { get; set; }

        [Column("city")]
        public string? City { get; set; }

        [Column("state")]
        public string? State { get; set; }

        [Column("zip")]
        public string? Zip { get; set; }

        [Column("email")]
        [Required]
        public string? Email { get; set; }

        [Column("password")]
        [Required]
        public string? Password { get; set; }

        [Column("username")]
        public string? Username { get; set; }

        public virtual ICollection<UserRole> UserRoles { get; set; }

        public User()
        {
            UserRoles = new HashSet<UserRole>();
        }
    }
}
