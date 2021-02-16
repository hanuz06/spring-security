package web.service;

import web.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findByUserName(String userName);
    User getUserById(Long id);
    List<User> listUsers();
    void addUser(User user);
    void updateUser(User user);
}
