package jointcase.dao.impl;

import jointcase.dao.AttachedFileDao;
import jointcase.model.AttachedFile;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AttachedFileDaoImpl extends AbstractDao<AttachedFile> implements AttachedFileDao {
    public AttachedFileDaoImpl(SessionFactory factory) {
        super(factory, AttachedFile.class);
    }
}
