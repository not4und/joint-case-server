package jointcase.service;

import java.util.List;
import jointcase.model.Event;

public interface EventService {
    Event add(Event event);

    Event update(Event event);

    Event get(Long id);

    List<Event> getAllByCategoryAndUser(Long categoryId, Long userId, boolean isPrivate);
}
