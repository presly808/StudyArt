package ua.artcode.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.artcode.dao.UserDao;
import ua.artcode.exception.NoSuchUserException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxim on 03.02.2016.
 */
@Service
public class MongoUserDetailsService implements UserDetailsService {

    private static final Logger LOG = Logger.getLogger(MongoUserDetailsService.class);

    @Autowired
    @Qualifier("userDaoMongoMongoImpl")
    private UserDao userDao;

    private org.springframework.security.core.userdetails.User userDetails;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;
        ua.artcode.model.common.User user;
        try {
            user=userDao.find(email);
        } catch (NoSuchUserException e) {
            LOG.trace("Entered wrong email.");
            throw new UsernameNotFoundException(e.getMessage());
        }

        List<GrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority(user.getUserType().toString()));
            userDetails = new org.springframework.security.core.userdetails.User(user.getName(),
                    user.getPassword(), enabled, accountNonExpired, credentialsNonExpired,
                    accountNonLocked, authList);
            return userDetails;
    }
}
