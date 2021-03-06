package web.dao;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import web.models.Role;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    public UserDaoImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.persist(user);
    }

    @Override
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        entityManager.merge(user);
    }
    
    @Override
    public Optional<User> findByUserName(String userName) {
        User user = entityManager.createQuery("select e from User e where e.userName = ?1", User.class)
                .setParameter(1, userName)
                .getSingleResult();
        return  Optional.of( user );
    }

    @Override
    public User getUserById(Long id) {
        return  entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        User userToBeDeleted = getUserById(id);
        if (userToBeDeleted!=null){
            entityManager.remove(userToBeDeleted);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Role> listRoles() {
        return entityManager.createQuery("FROM Role", Role.class).getResultList();
    }

    @Override
    public Role findByRoleId(Long id) {
        return  entityManager.find(Role.class, id);
    }
}
