package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.models.Role;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private UserDao userDaoImpl;

    public RoleServiceImpl(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Transactional
    @Override
    public List<Role> listRoles() {
        return userDaoImpl.listRoles();
    }

    @Transactional
    @Override
    public Role findByRoleId(Long id) {
        return userDaoImpl.findByRoleId(id);
    }
}
