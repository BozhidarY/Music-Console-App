package MusicConsoleApp.View;

import MusicConsoleApp.Controller.AdminController;
import MusicConsoleApp.Controller.ArtistController;
import MusicConsoleApp.Controller.ClientController;
import MusicConsoleApp.Controller.SignMenuController;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Client;
import MusicConsoleApp.Models.Users;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.Scanner;

public class SignUpMenu {
    private final Logger logger = (Logger) LogManager.getLogger(SignUpMenu.class);
    Scanner scanner = new Scanner(System.in);
    private SignMenuController signMenuController;

    public SignUpMenu(SignMenuController signMenuController) {
        this.signMenuController = signMenuController;
    }

    public void login() {
        System.out.println("Login Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();

        Users user = signMenuController.checkIfUserExists(username, password);
        if (user instanceof Artist artist) {
            logger.info("User has loggined: username={}", username);
            ArtistController artistController = new ArtistController(artist, signMenuController.getUserDB(), signMenuController.getSongData());
            ArtistView artistView = new ArtistView(artistController);
            artistView.openArtistCommunication();
        } else if (user instanceof Client client) {
            logger.info("User has loggined: username={}", username);
            ClientController clientController = new ClientController(client, signMenuController.getUserDB(), signMenuController.getSongData());
            ClientView clientView = new ClientView(clientController);
            clientView.openClientCommunication();
        } else if (user instanceof Admin) {
            logger.info("The admin has been loggined: username={}", username);
            AdminController adminController = new AdminController(Admin.getAdmin(), signMenuController.getUserDB(), signMenuController.getDeletedUsers());
            AdminView adminView = new AdminView(adminController);
            adminView.openAdminCommunication();
        } else if (user == null) {
            System.out.println("The user with this credentials doesn't exist. Do you want to register or try again?");
            String choice = scanner.nextLine();
            chooseSignInOption(choice);
        }
    }

    public void register() {
        System.out.println("Register Form: ");
        System.out.println("Enter username");
        String username = scanner.nextLine();
        System.out.println("Enter password");
        String password = scanner.nextLine();
        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        if (!signMenuController.checkDublicateUser(username)) {
            System.out.println("Username is taken. Try again or go to login(Register/ Login)");
            String choice = scanner.nextLine();
            chooseSignInOption(choice);
        } else if (!signMenuController.validateUserCredentials(username, password)) {
            System.out.println("Try again or go to login(Register/ Login)");
            String choice = scanner.nextLine();
            chooseSignInOption(choice);
        } else {
            System.out.println("Do you want to create Client or Artist account.\n (Client/Artist)");
            String choice = scanner.nextLine();
            switch (choice) {
                case "Artist" -> {
                    Artist artist = new Artist(username, hashedPassword);
                    signMenuController.getUserDB().getUsersList().add(artist);
                    System.out.println("You have registered successfully");
                    logger.info("User registered successfully: username={} as {}", username, artist.getUserType());
                    ArtistController artistController = new ArtistController(artist, signMenuController.getUserDB(), signMenuController.getSongData());
                    ArtistView artistView = new ArtistView(artistController);
                    artistView.openArtistCommunication();
                }
                case "Client" -> {
                    Client client = new Client(username, hashedPassword);
                    signMenuController.getUserDB().getUsersList().add(client);
                    System.out.println("You have registered successfully");
                    logger.info("User registered successfully: username={} as {}", username, client.getUserType());
                    ClientController clientController = new ClientController(client, signMenuController.getUserDB(), signMenuController.getSongData());
                    ClientView clientView = new ClientView(clientController);
                    clientView.openClientCommunication();
                }
            }
        }
    }

    public void chooseSignInOption(String choice) {
        if (choice.equals("Login")) {
            login();
        } else if (choice.equals("Register")) {
            register();
        } else {
            System.out.println("Wrong command. Program exiting.");
        }
    }
}
