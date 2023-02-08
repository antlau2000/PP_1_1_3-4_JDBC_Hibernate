package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Steve", "Jobs", (byte) 45);
        userDao.saveUser("Tony", "Stark", (byte) 54);
        userDao.saveUser("Cathrine", "Jones", (byte) 35);
        userDao.saveUser("Jim", "Carrey", (byte) 26);
        userDao.getAllUsers().forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
