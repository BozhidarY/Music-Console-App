package MusicConsoleApp.UsersEngine;



import MusicConsoleApp.CommunicationEngine.UserCommunication;
import MusicConsoleApp.Users.*;

import java.util.Scanner;

public class LoginRegisterMenu {
    Scanner scanner = new Scanner(System.in);
    UserCommunication userCommunication = new UserCommunication();

    public void login(UserDB userDB) {
        boolean hasLoginOccured = false;

        System.out.println("Login Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(username) && password.equals(user.getPassword())) {
                if (user.getUserType() == UserType.ARTIST) {
                    hasLoginOccured = true;
                    userCommunication.openArtistCommunication((Artist) user);
                } else if (user.getUserType() == UserType.CLIENT) {
                    hasLoginOccured = true;
                    userCommunication.openClientCommunication((Client) user, userDB);
                }
            }
        }
        if (username.equals(Admin.getAdmin().getUsername()) && password.equals(Admin.getAdmin().getPassword())) {
            hasLoginOccured = true;
            userCommunication.openAdminCommunication(Admin.getAdmin(), userDB);
        }
        if (!hasLoginOccured) {
            System.out.println("No such credentials. You need to register");
            register(userDB);
        }
    }

    public void register(UserDB userDB) {

        System.out.println("Register Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        boolean dublicationCheck = false;

        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(username)) {
                System.out.println("The username is taken");
                dublicationCheck = true;
            }
        }
        if (!dublicationCheck) {
            System.out.println("Do you want to create Client or Artist account.\n (Client/Artist)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "Artist" -> {
                    Artist artist = new Artist(username, password);
                    userDB.getUsersList().add(artist);
                    System.out.println("You have registered successfully");
                    userCommunication.openArtistCommunication(artist);
                }
                case "Client" -> {
                    Client client = new Client(username, password);
                    userDB.getUsersList().add(client);
                    System.out.println("You have registered successfully");
                    userCommunication.openClientCommunication(client, userDB);
                }
            }
        } else {
            System.out.println("Try register again or go to login form(Register/Login)");
            String choice = scanner.nextLine();
            if (choice.equals("Register")) {
                register(userDB);
            } else if (choice.equals("Login")) {
                login(userDB);
            }
        }
    }
}
