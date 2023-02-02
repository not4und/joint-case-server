package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.Status;

public interface StatusDao {
    Status add(Status status);

    Optional<Status> get(Long id);

    List<Status> getAll();
}
