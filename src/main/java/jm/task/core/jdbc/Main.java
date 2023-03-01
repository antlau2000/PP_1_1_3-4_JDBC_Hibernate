package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Tony", "Stark", (byte) 54);
        userService.saveUser("Cathrine", "Jones", (byte) 35);
        userService.saveUser("Jim", "Carrey", (byte) 26);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.closeSessionFactory();
    }
}
