package jointcase.dao.fs.impl;

import javax.servlet.ServletContext;
import jointcase.dao.fs.AvatarFsDao;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarFsDaoImpl extends AbstractFileStorageDao implements AvatarFsDao {
    public AvatarFsDaoImpl(ServletContext servletContext) {
        super(servletContext, "avatars");
    }
}
