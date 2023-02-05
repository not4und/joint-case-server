package jointcase.service;

import java.util.Optional;
import jointcase.model.User;

public interface UserService {
    User add(User user);

    User get(Long id);

    Optional<User> findByUsername(String username);
}
