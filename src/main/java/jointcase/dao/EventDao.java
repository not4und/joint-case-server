package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.Event;

public interface EventDao {
    Event add(Event event);
    Event update(Event event);

    Optional<Event> get(Long id);

    List<Event> getAll();

    void delete(Long id);
}
