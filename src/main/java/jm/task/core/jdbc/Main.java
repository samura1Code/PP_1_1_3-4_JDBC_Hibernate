package jm.task.core.jdbc;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Dmitriy", "Petrov", (byte) 40);
        userService.saveUser("Vitaliy", "Vitaliev", (byte) 25);
        userService.saveUser("Petr", "Perov", (byte) 80);
        userService.saveUser("Valeriy", "Makurin", (byte) 29);
        for (int i = 0; i < userService.getAllUsers().size(); i++) {
           System.out.println(userService.getAllUsers().get(i));

        }

       userService.cleanUsersTable();
       userService.dropUsersTable();

    }
}
