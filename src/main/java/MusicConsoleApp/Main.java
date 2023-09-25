package MusicConsoleApp;


import MusicConsoleApp.Controller.UserControllers.AdminController;
import MusicConsoleApp.Controller.UserControllers.ArtistController;
import MusicConsoleApp.Controller.UserControllers.ClientController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.View.LoginRegisterMenu;
import MusicConsoleApp.View.AdminView;
import MusicConsoleApp.View.ArtistView;
import MusicConsoleApp.View.ClientView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();
        LoginRegisterMenu loginRegisterMenu = new LoginRegisterMenu();
        Scanner scanner = new Scanner(System.in);

        UserDB userDB;
        userDB = loadSaveUsersToJson.loadUsers(Constants.USERS_JSON_PATH);

        System.out.println("Login/Register");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Login" ->{
                Users user = loginRegisterMenu.login(userDB);
                if(user instanceof Client client){
                    ClientView clientView = new ClientView();
                    ClientController clientController = new ClientController(client, clientView);
                    clientController.openClientCommunication(userDB);
                }
                if(user instanceof Artist artist){
                    ArtistView artistView = new ArtistView();
                    ArtistController artistController = new ArtistController(artist, artistView);
                    artistController.openArtistCommunication(userDB);
                }
                if(user instanceof Admin admin){
                    AdminView adminView = new AdminView();
                    AdminController adminController = new AdminController(admin, adminView);
                    adminController.openAdminCommunication(userDB);
                }
            }

            case "Register" -> {
                Users user = loginRegisterMenu.register(userDB);
                if(user instanceof Client client){
                    ClientView clientView = new ClientView();
                    ClientController clientController = new ClientController(client, clientView);
                    clientController.openClientCommunication(userDB);
                }
                if(user instanceof Artist artist){
                    ArtistView artistView = new ArtistView();
                    ArtistController artistController = new ArtistController(artist, artistView);
                    artistController.openArtistCommunication(userDB);
                }
                if(user instanceof Admin admin){
                    AdminView adminView = new AdminView();
                    AdminController adminController = new AdminController(admin, adminView);
                    adminController.openAdminCommunication(userDB);
                }
            }
        }

        loadSaveUsersToJson.saveUsers(Constants.USERS_JSON_PATH, userDB);
    }
}
