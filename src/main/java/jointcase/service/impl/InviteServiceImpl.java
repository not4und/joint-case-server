package jointcase.service.impl;

import java.util.List;
import jointcase.dao.InviteDao;
import jointcase.model.Invite;
import jointcase.service.InviteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InviteServiceImpl implements InviteService {
    private final InviteDao inviteDao;

    @Override
    public Invite add(Invite invite) {
        return inviteDao.add(invite);
    }

    @Override
    public Invite get(Long id) {
        return inviteDao.get(id).orElseThrow(
                () -> new RuntimeException("Invite with id: " + id + " not found.")
        );
    }

    @Override
    public List<Invite> getAllByUser(Long userId) {
        return inviteDao.getAllByUser(userId);
    }

    @Override
    public void delete(Long id) {
        inviteDao.delete(id);
    }
}
