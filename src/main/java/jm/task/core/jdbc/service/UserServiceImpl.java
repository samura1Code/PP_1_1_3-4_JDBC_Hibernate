package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl extends UserDaoJDBCImpl implements UserService {

 private final UserDao userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
//        super.createUsersTable();
    }


    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
//        super.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDaoHibernate.saveUser(name, lastName, age);
//        super.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
//      super.removeUserById(id);

    }

    public List<User> getAllUsers() {
//        return super.getAllUsers();
          return userDaoHibernate.getAllUsers();
    }

    public void cleanUsersTable() {
//        super.cleanUsersTable();
          userDaoHibernate.cleanUsersTable();
    }
}
