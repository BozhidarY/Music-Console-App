package MusicConsoleApp;


import MusicConsoleApp.Controller.UserControllers.AdminController;
import MusicConsoleApp.Controller.UserControllers.ArtistController;
import MusicConsoleApp.Controller.UserControllers.ClientController;
import MusicConsoleApp.View.ClientView;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.View.LoginRegisterMenu;
import MusicConsoleApp.View.AdminView;
import MusicConsoleApp.View.ArtistView;

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
            case "Login" -> loginRegisterMenu.login(userDB);
            case "Register" -> loginRegisterMenu.register(userDB);
        }

        loadSaveUsersToJson.saveUsers(Constants.USERS_JSON_PATH, userDB);
    }
}
