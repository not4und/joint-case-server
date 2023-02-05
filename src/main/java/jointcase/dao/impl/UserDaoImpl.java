package jointcase.dao.impl;

import java.util.Optional;
import jointcase.dao.UserDao;
import jointcase.exception.DataProcessingException;
import jointcase.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    private static final String SELECT = "FROM User u "
            + "LEFT JOIN FETCH u.events ";

    public UserDaoImpl(SessionFactory factory) {
        super(factory, User.class);
    }

    @Override
    public Optional<User> get(Long id) {
        try (Session session = factory.openSession()) {
            return session.createQuery(SELECT, User.class)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get user by id: " + id, e);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try (Session session = factory.openSession()) {
            return session.createQuery(SELECT
                            + "WHERE u.name = :username OR u.email = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user with name: " + username, e);
        }
    }
}
