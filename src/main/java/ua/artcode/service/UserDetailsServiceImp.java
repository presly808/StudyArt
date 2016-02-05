package ua.artcode.service;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.GrantedAuthority;
        import org.springframework.security.core.authority.SimpleGrantedAuthority;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import ua.artcode.exception.NoSuchUserException;
        import ua.artcode.model.common.User;
        import ua.artcode.model.common.UserType;

        import java.util.HashSet;
        import java.util.Set;

/**
 * Created by Razer on 01.02.16.
 */
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=null;
        try {
            user = userService.getUser(email);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserType.ROLE_USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        roles);
        return userDetails;
    }
}
