package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("""
                        CREATE TABLE IF NOT EXISTS User (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `name` VARCHAR(45) NOT NULL,
                          `last_name` VARCHAR(45) NOT NULL,
                          `age` INT(3) NOT NULL,
                          PRIMARY KEY (`id`));""");
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("DROP TABLE IF EXISTS User;");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            User user = new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE User;");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
