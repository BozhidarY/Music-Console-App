package MusicConsoleApp;


import MusicConsoleApp.Controller.UserControllers.SignMenuController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.*;
import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.View.SignUpMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();

        Scanner scanner = new Scanner(System.in);

        UserDB userDB = loadSaveUsersToJson.loadUsers(Constants.USERS_JSON_PATH);
        SignMenuController signMenuController = new SignMenuController(userDB);
        SignUpMenu signUpMenu = new SignUpMenu(signMenuController);

        System.out.println("Login/Register");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Login" -> signUpMenu.login(userDB);
            case "Register" -> signUpMenu.register(userDB);
        }

        loadSaveUsersToJson.saveUsers(Constants.USERS_JSON_PATH, userDB);
    }
}
