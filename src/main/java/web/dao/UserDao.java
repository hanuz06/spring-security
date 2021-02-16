package web.dao;

import web.models.Role;
import web.models.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    Optional<User> findByUserName(String userName);
    User getUserById(Long id);
    void addUser(User user);
    void updateUser(User user);
    List<User> listUsers();

    List<Role> listRoles();
    Role findByRoleId(Long id);
}