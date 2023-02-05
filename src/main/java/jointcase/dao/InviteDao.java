package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.Invite;

public interface InviteDao {
    Invite add(Invite invite);

    Optional<Invite> get(Long id);

    List<Invite> getAllByUser(Long id);

    void delete(Long id);
}
