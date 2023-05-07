namespace Magazin.Models
{
    [Table("UserRoles")]
    public class UserRole
    {
        [Key]
        [Column("user_role_id")]
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        public long Id { get; set; }

        [ForeignKey(nameof(User))]
        [Column("user_id")]
        public long UserId { get; set; }
        public virtual User User { get; set; }

        [ForeignKey(nameof(Role))]
        [Column("role_id")]
        public long RoleId { get; set; }
        public virtual Role Role { get; set; }

        public UserRole() { }
    }
}
