package jointcase.dao.impl;

import java.util.Optional;
import jointcase.dao.StatusDao;
import jointcase.exception.DataProcessingException;
import jointcase.model.Status;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class StatusDaoImpl extends AbstractDao<Status> implements StatusDao {
    public StatusDaoImpl(SessionFactory factory) {
        super(factory, Status.class);
    }

    @Override
    public Optional<Status> getByName(Status.StatusType name) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Status s "
                    + "WHERE s.type = :name", Status.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get Status by name: " + name, e);
        }
    }
}
