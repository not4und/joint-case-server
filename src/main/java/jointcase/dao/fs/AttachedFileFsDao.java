package jointcase.dao.fs;

import org.springframework.web.multipart.MultipartFile;

public interface AttachedFileFsDao {
    String add(MultipartFile file);

    byte[] get(String path);

    void delete(String path);
}
