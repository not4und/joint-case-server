package jointcase.dao.impl;

import jointcase.dao.StatusDao;
import jointcase.model.Status;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDaoImpl extends AbstractDao<Status> implements StatusDao {
    public StatusDaoImpl(SessionFactory factory) {
        super(factory, Status.class);
    }
}
