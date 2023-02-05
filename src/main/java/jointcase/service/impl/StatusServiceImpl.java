package jointcase.service.impl;

import java.util.List;
import jointcase.dao.StatusDao;
import jointcase.model.Status;
import jointcase.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusDao statusDao;

    @Override
    public Status add(Status status) {
        return statusDao.add(status);
    }

    @Override
    public Status get(Long id) {
        return statusDao.get(id).orElseThrow(
                () -> new RuntimeException("Status with id: " + id + " not found.")
        );
    }

    @Override
    public Status getByName(Status.StatusType name) {
        return statusDao.getByName(name).orElseThrow(
                () -> new RuntimeException("Status with name: " + name + " not found.")
        );
    }

    @Override
    public List<Status> getAll() {
        return statusDao.getAll();
    }
}
