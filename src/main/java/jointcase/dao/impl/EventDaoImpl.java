package jointcase.dao.impl;

import java.util.List;
import java.util.Optional;
import jointcase.dao.EventDao;
import jointcase.exception.DataProcessingException;
import jointcase.model.Event;
import jointcase.model.Status;
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
                        + "LEFT JOIN FETCH e.files "
                        + "LEFT JOIN FETCH e.members "
                        + "WHERE e.status != :closed", Event.class)
                    .setParameter("closed", Status.StatusType.CLOSED)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get event by id: " + id, e);
        }
    }

    @Override
    public List<Event> getAllByCategoryAndUser(Long categoryId, Long userId, boolean isPrivate) {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM Event e "
                         + "LEFT JOIN FETCH e.members m "
                         + "WHERE e.category.id = :categoryId AND (e.owner.id = :userId "
                         + "OR m.id = :userId) "
                         + (isPrivate ? "" : "AND e.isPrivate = false"),
                            Event.class)
                    .setParameter("categoryId", categoryId)
                    .setParameter("userId", userId)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get event by category id: " + categoryId, e);
        }
    }
}
