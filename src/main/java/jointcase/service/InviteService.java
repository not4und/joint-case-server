package jointcase.service;

import java.util.List;
import jointcase.model.Invite;

public interface InviteService {
    Invite add(Invite invite);

    Invite get(Long id);

    List<Invite> getAllByUser(Long userId);

    void delete(Long id);
}
