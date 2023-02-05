package jointcase.security.impl;

import jointcase.model.User;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Can't find user by username: " + username)
        );
        UserBuilder userBuilder = org.springframework.security.core.userdetails.User
                .withUsername(username);
        userBuilder.password(user.getPassword());
        userBuilder.roles(UserStatus.AUTHORIZED.name());
        return userBuilder.build();
    }

    public enum UserStatus {
        AUTHORIZED
    }
}
