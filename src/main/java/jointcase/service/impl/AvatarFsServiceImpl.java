package jointcase.service.impl;

import jointcase.dao.fs.AvatarFsDao;
import jointcase.service.AvatarFsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class AvatarFsServiceImpl implements AvatarFsService {
    private final AvatarFsDao avatarFsDao;

    @Override
    public String add(MultipartFile file) {
        return avatarFsDao.add(file);
    }

    @Override
    public byte[] get(String path) {
        return avatarFsDao.get(path);
    }

    @Override
    public void delete(String path) {
        avatarFsDao.delete(path);
    }
}
