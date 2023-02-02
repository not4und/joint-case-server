package jointcase.dao.impl;

import java.util.List;
import java.util.Optional;

import jointcase.exception.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory factory;
    private final Class<T> clazz;

    public AbstractDao(SessionFactory factory, Class<T> clazz) {
        this.factory = factory;
        this.clazz = clazz;
    }

    public T add(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            transactionRollback(transaction);
            throw new DataProcessingException("Can`t insert "
                    + clazz.getSimpleName() + ": " + t, e);
        } finally {
            sessionClose(session);
        }
    }

    public T update(T t) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        } catch (Exception e) {
            transactionRollback(transaction);
            throw new DataProcessingException("Can't update "
                    + clazz.getSimpleName() + ": " + t, e);
        } finally {
            sessionClose(session);
        }
    }

    public Optional<T> get(Long id) {
        try (Session session = factory.openSession()) {
            return Optional.ofNullable(session.get(clazz, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get "
                    + clazz.getSimpleName() + " by id: " + id, e);
        }
    }

    public List<T> getAll() {
        try (Session session = factory.openSession()) {
             return session.createQuery("FROM " + clazz.getSimpleName(), clazz)
                     .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get all "
                    + clazz.getSimpleName() + "s from DB.", e);
        }
    }

    public void delete(Long id) {
        Session session = null;
        Transaction transaction  = null;
        try{
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.delete(id);
            transaction.commit();
        }catch (Exception e){
            transactionRollback(transaction);
            throw new DataProcessingException("Can't delete "
                    + clazz.getSimpleName() + ": " + id, e);
        } finally {
            sessionClose(session);
        }
    }

    protected void transactionRollback(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }

    protected void sessionClose(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
