package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {
    private UserDao userDaoImpl;

    public MyUserDetailsService(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userDaoImpl.findByUserName(userName).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", userName)));
    }
}

