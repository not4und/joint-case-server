package jointcase.dao.fs.impl;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;
import javax.servlet.ServletContext;
import jointcase.exception.DataProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public abstract class AbstractFileStorageDao {
    protected final ServletContext servletContext;
    private final String folder;

    public String add(MultipartFile file) {
        try {
            String filepath = getFilePath()
                    + UUID.randomUUID()
                    + "-"
                    + file.getOriginalFilename();
            file.transferTo(new File(filepath));
            return filepath;
        } catch (Exception e) {
            throw new DataProcessingException("Can't save file: "
                    + file.getOriginalFilename(), e);
        }
    }

    public byte[] get(String path) {
        try {
            return Files.readAllBytes(new File(path).toPath());
        } catch (Exception e) {
            throw new DataProcessingException("Can't get file by path: " + path, e);
        }
    }

    public void delete(String path) {
        new File(path).delete();
    }

    private String getFilePath() {
        return servletContext.getRealPath("/")
                + "resources"
                + File.separator
                + "files"
                + File.separator
                + folder
                + File.separator;
    }
}
