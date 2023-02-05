package jointcase.dao.impl;

import java.util.List;
import jointcase.dao.InviteDao;
import jointcase.exception.DataProcessingException;
import jointcase.model.Invite;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDaoImpl extends AbstractDao<Invite> implements InviteDao {
    public InviteDaoImpl(SessionFactory factory) {
        super(factory, Invite.class);
    }

    @Override
    public List<Invite> getAllByUser(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Invite i "
                        + "WHERE i.receiver.id = :id", Invite.class)
                    .setParameter("id", id)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all Invites by userId: " + id, e);
        }
    }
}
