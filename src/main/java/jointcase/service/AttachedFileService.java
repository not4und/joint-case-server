package jointcase.service;

import jointcase.model.AttachedFile;

public interface AttachedFileService {
    AttachedFile add(AttachedFile attachedFile);

    AttachedFile get(Long id);

    void delete(Long id);
}
