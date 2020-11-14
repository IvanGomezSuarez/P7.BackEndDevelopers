package tests;

import java.util.List;
import entities.User;
import service.UserService;

public class UserTest {
	 
    public static void main(String[] args) {
        UserService userService = new UserService();
        User user1 = new User("cristianvia", "cristian@mail.com", "pass");
        User user2 = new User("juanito", "juanito@mail.com", "pass");
        System.out.println("*** Persist - start ***");
        userService.persist(user1);
        userService.persist(user2);
        List<User> users1 = userService.findAll();
        System.out.println("Users Persisted are :");
        for (User b : users1) {
            System.out.println("-" + b.toString());
        }
        System.out.println("*** Persist - end ***");
         System.exit(0);
    }
}