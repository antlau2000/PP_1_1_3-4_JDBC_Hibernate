package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserDao userDao = new UserDaoHibernateImpl();
        userService.createUsersTable();
        userDao.saveUser("Steve", "Jobs", (byte) 45);
        userService.saveUser("Tony", "Stark", (byte) 54);
        userService.saveUser("Cathrine", "Jones", (byte) 35);
        userService.saveUser("Jim", "Carrey", (byte) 26);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
