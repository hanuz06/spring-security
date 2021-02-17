package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDaoImpl;

    public UserServiceImpl(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Transactional
    @Override
    public void addUser(User user) {
        userDaoImpl.addUser(user);
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        userDaoImpl.updateUser(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        userDaoImpl.deleteUser(id);
    }

    @Transactional
    @Override
    public Optional<User> findByUserName(String userName) {
        return userDaoImpl.findByUserName(userName);
    }

    @Transactional
    @Override
    public User getUserById(Long id) {
       return userDaoImpl.getUserById(id);
    }

    @Transactional
    @Override
    public List<User> listUsers() {
        return userDaoImpl.listUsers();
    }
}
