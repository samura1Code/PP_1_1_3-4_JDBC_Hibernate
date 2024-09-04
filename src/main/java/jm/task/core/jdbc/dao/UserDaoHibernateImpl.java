package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    Transaction transaction = null;
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernateImpl.class);

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS `kata_db`.`users` " +
                        "(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50), lastName VARCHAR(50), age INT)");
                query.executeUpdate();
                transaction.commit();
                logger.info("Method 'createUsersTable': Table 'users' successfully created (if not already existing).");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'createUsersTable': Error while creating table 'users'. Exception: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("DROP TABLE IF EXISTS `kata_db`.`users`");
                query.executeUpdate();
                transaction.commit();
                logger.info("Method 'dropUsersTable': Table 'users' successfully dropped.");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'dropUsersTable': Error while dropping table 'users'. Exception: {}", e.getMessage(), e);
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.save(user);
                transaction.commit();
                logger.info("Method 'saveUser': User with name '{}' and last name '{}' successfully saved.", name, lastName);
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'saveUser': Error while saving user with name '{}' and last name '{}'. Exception: {}", name, lastName, e.getMessage(), e);
            }
        }
    }

    @Override
    public void removeUserById(long id) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                    transaction.commit();
                    logger.info("Method 'removeUserById': User with id '{}' successfully deleted.", id);
                } else {
                    transaction.commit();
                    logger.warn("Method 'removeUserById': No user found with id '{}'.", id);
                }
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'removeUserById': Error while deleting user with id '{}'. Exception: {}", id, e.getMessage(), e);
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                users = session.createQuery("FROM User", User.class).list();
                transaction.commit();
                logger.info("Method 'getAllUsers': Successfully retrieved {} users.", users.size());
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'getAllUsers': Error while retrieving all users. Exception: {}", e.getMessage(), e);
            }
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("DELETE FROM `kata_db`.`users`");
                query.executeUpdate();
                transaction.commit();
                logger.info("Method 'cleanUsersTable': Table 'users' successfully cleaned.");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                logger.error("Method 'cleanUsersTable': Error while cleaning table 'users'. Exception: {}", e.getMessage(), e);
            }
        }
    }
}
