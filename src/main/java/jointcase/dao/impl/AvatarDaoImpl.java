package jointcase.dao.impl;

import jointcase.dao.AvatarDao;
import jointcase.model.Avatar;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AvatarDaoImpl extends AbstractDao<Avatar> implements AvatarDao {
    public AvatarDaoImpl(SessionFactory factory) {
        super(factory, Avatar.class);
    }
}
