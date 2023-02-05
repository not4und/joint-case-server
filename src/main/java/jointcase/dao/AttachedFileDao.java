package jointcase.dao;

import java.util.Optional;
import jointcase.model.AttachedFile;

public interface AttachedFileDao {
    AttachedFile add(AttachedFile attachedFile);

    Optional<AttachedFile> get(Long id);

    void delete(Long id);
}
