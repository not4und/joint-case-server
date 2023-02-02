package jointcase.dao.impl;

import java.util.List;
import java.util.Optional;
import jointcase.dao.EventDao;
import jointcase.exception.DataProcessingException;
import jointcase.model.Event;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class EventDaoImpl extends AbstractDao<Event> implements EventDao {
    public EventDaoImpl(SessionFactory factory) {
        super(factory, Event.class);
    }

    @Override
    public Optional<Event> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Event e "
                        + "JOIN FETCH e.files "
                        + "JOIN FETCH e.members ", Event.class)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get event by id: " + id, e);
        }
    }

    @Override
    public List<Event> getAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Event e ", Event.class)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all events from DB.", e);
        }
    }
}
