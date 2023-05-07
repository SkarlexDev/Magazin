namespace Magazin.Data.Services
{
    public interface IUserService
    {

        User? getUserId(long id);

        User? getUserEmail(string email);

        void addUser(User user);

        void updateUser(User user);

        bool checkEmail(string email);
    }
}
