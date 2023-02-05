package jointcase.dao;

import java.util.Optional;
import jointcase.model.User;

public interface UserDao {
    User add(User user);

    Optional<User> get(Long id);

    Optional<User> findByUsername(String username);
}
