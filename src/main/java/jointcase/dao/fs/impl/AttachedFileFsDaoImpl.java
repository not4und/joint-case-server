package jointcase.dao.fs.impl;

import javax.servlet.ServletContext;
import jointcase.dao.fs.AttachedFileFsDao;
import org.springframework.stereotype.Repository;

@Repository
public class AttachedFileFsDaoImpl extends AbstractFileStorageDao implements AttachedFileFsDao {
    public AttachedFileFsDaoImpl(ServletContext servletContext) {
        super(servletContext, "events");
    }
}
