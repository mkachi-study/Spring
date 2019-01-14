package Mapper;

import Model.User;
import java.util.List;

public interface UserMapper {
    boolean insert(String name);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean deleteAllUsers();
}