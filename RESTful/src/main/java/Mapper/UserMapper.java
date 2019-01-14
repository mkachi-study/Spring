package Mapper;

import Model.User;
import java.util.List;

public interface UserMapper {
    List<User> getUsers();
    boolean insert(String name);
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean deleteAllUsers();
}