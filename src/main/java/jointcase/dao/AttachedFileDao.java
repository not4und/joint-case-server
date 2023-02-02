package jointcase.dao;

import java.util.List;
import java.util.Optional;
import jointcase.model.AttachedFile;

public interface AttachedFileDao {
    AttachedFile add(AttachedFile attachedFile);
    AttachedFile update(AttachedFile attachedFile);

    Optional<AttachedFile> get(Long id);

    List<AttachedFile> getAll();

    void delete(Long id);
}
