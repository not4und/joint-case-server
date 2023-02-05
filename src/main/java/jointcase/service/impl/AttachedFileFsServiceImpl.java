package jointcase.service.impl;

import jointcase.dao.fs.AttachedFileFsDao;
import jointcase.service.AttachedFileFsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AttachedFileFsServiceImpl implements AttachedFileFsService {
    private final AttachedFileFsDao attachedFileFsDao;

    @Override
    public String add(MultipartFile file) {
        return attachedFileFsDao.add(file);
    }

    @Override
    public byte[] get(String path) {
        return attachedFileFsDao.get(path);
    }

    @Override
    public void delete(String path) {
        attachedFileFsDao.delete(path);
    }
}
