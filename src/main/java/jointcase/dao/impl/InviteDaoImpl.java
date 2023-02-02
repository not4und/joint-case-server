package jointcase.dao.impl;

import jointcase.dao.InviteDao;
import jointcase.model.Invite;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InviteDaoImpl extends AbstractDao<Invite> implements InviteDao {
    public InviteDaoImpl(SessionFactory factory) {
        super(factory, Invite.class);
    }
}
