package jointcase.service;

import org.springframework.web.multipart.MultipartFile;

public interface AttachedFileFsService {
    String add(MultipartFile file);

    byte[] get(String path);

    void delete(String path);
}
