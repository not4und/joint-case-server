package jointcase.service.impl;

import java.util.List;
import jointcase.dao.EventDao;
import jointcase.model.Event;
import jointcase.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventDao eventDao;

    @Override
    public Event add(Event event) {
        return eventDao.add(event);
    }

    @Override
    public Event update(Event event) {
        return eventDao.update(event);
    }

    @Override
    public Event get(Long id) {
        return eventDao.get(id).orElseThrow(
                () -> new RuntimeException("Event with id: " + id + " not found.")
        );
    }

    @Override
    public List<Event> getAllByCategoryAndUser(Long categoryId, Long userId, boolean isPrivate) {
        return eventDao.getAllByCategoryAndUser(categoryId, userId, isPrivate);
    }
}
