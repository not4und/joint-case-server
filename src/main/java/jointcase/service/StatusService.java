package jointcase.service;

import java.util.List;
import jointcase.model.Status;

public interface StatusService {
    Status add(Status status);

    Status get(Long id);

    Status getByName(Status.StatusType name);

    List<Status> getAll();
}
