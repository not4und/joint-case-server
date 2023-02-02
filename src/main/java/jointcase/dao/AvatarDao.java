package jointcase.dao;

import java.util.Optional;
import jointcase.model.Avatar;

public interface AvatarDao {
    Avatar add(Avatar avatar);

    Avatar update(Avatar avatar);

    Optional<Avatar> get(Long id);

    void delete(Long id);
}
