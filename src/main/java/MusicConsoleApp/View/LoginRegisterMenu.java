package MusicConsoleApp.View;



import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Exceptions.Validators;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Client;
import MusicConsoleApp.Models.Users;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

//import static MusicConsoleApp.Models.Admin.admin;

public class LoginRegisterMenu {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService();
    Validators validators = new Validators();

    private final Logger logger = (Logger) LogManager.getLogger(LoginRegisterMenu.class);

    public Users login(UserDB userDB) {
        boolean hasLoginOccured = false;

        System.out.println("Login Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(username)) {
                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if(result.verified){
                    if (user instanceof Artist artist) {
                        hasLoginOccured = true;
                        logger.info("User has loggined: username={}", username);
                        return artist;
                    } else if (user instanceof Client client) {
                        hasLoginOccured = true;
                        logger.info("User has loggined: username={}", username);
                        return client;
                    }
                }
            }
        }
        if (username.equals(Admin.getAdmin().getUsername()) && password.equals(Admin.getAdmin().getPassword())) {
            hasLoginOccured = true;
            logger.info("The admin has been loggined: username={}", username);
            return Admin.getAdmin();
        }
        if (!hasLoginOccured) {
            System.out.println("No such credentials. You need to register");
            register(userDB);
        }
        return null;
    }

    public Users register(UserDB userDB) {
        System.out.println("Register Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        boolean dublicationCheck = false;

        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(username)) {
                System.out.println("The username is taken");
                dublicationCheck = true;
            }
        }
        if (!dublicationCheck && validators.validateUsername(username) && validators.validatePassword(password)) {
            System.out.println("Do you want to create Client or Artist account.\n (Client/Artist)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "Artist" -> {
                    Artist artist = new Artist(username, hashedPassword);
                    userDB.getUsersList().add(artist);
                    System.out.println("You have registered successfully");
                    logger.info("User registered successfully: username={} as {}", username, artist.getUserType() );
                    return artist;
                }
                case "Client" -> {
                    Client client = new Client(username, hashedPassword);
                    userDB.getUsersList().add(client);
                    System.out.println("You have registered successfully");
                    logger.info("User registered successfully: username={} as {}", username, client.getUserType() );
                    return client;
                }
            }
        } else {
            System.out.println("Try register again or go to login form(Register/Login)");
            logger.warn("Registration failed due to duplicate username: username={}", username);
            String choice = scanner.nextLine();
            if (choice.equals("Register")) {
                register(userDB);
            } else if (choice.equals("Login")) {
                login(userDB);
            }
        }
        return null;
    }
}
