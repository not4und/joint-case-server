package jointcase.security.impl;

import java.util.Optional;
import jointcase.exception.AuthException;
import jointcase.model.User;
import jointcase.security.AuthService;
import jointcase.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(String email, String username, String password)
            throws AuthException {
        if (userService.findByUsername(email).isEmpty()) {
            return userService.add(User.builder()
                    .email(email)
                    .name(username)
                    .password(password)
                    .build());
        } else {
            throw new AuthException("That email is already used.");
        }
    }

    @Override
    public User signin(String username, String password) throws AuthException {
        Optional<User> user = userService.findByUsername(username);
        if (user.isEmpty() || !passwordEncoder.matches(password, user.get().getPassword())) {
            throw new AuthException("Incorrect data.");
        }
        return user.get();
    }
}
