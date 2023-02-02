package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.User;

public interface UserDao {
    User add(User user);

    User update(User user);

    Optional<User> get(Long id);

    List<User> getAll();

    void delete(Long id);
}
