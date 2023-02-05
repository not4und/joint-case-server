package jointcase.service.impl;

import jointcase.dao.AttachedFileDao;
import jointcase.model.AttachedFile;
import jointcase.service.AttachedFileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AttachedFileServiceImpl implements AttachedFileService {
    private final AttachedFileDao attachedFileDao;

    @Override
    public AttachedFile add(AttachedFile attachedFile) {
        return attachedFileDao.add(attachedFile);
    }

    @Override
    public AttachedFile get(Long id) {
        return attachedFileDao.get(id).orElseThrow(
                () -> new RuntimeException("File with id: " + id + " not found.")
        );
    }

    @Override
    public void delete(Long id) {
        attachedFileDao.delete(id);
    }
}
