package Mapper;

import Model.User;
import java.util.List;

public interface UserMapper {
    User getById(int id);
    boolean insert(String name);
    List<User> getUsers();
    boolean updateUser(User user);
    boolean deleteUser(int id);
    boolean deleteAllUsers();
}