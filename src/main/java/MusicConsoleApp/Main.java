package MusicConsoleApp;

import MusicConsoleApp.Controller.SignMenuController;
import MusicConsoleApp.DB.FileHandling.LoadSaveUsers;
import MusicConsoleApp.DB.FileHandling.LoadSaveSongs;
import MusicConsoleApp.DB.SongDB;
import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.View.SignUpMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        LoadSaveUsers loadSaveUsers = new LoadSaveUsers();
        LoadSaveSongs loadSaveSongs = new LoadSaveSongs();

        UserDB userDB = loadSaveUsers.loadUsersFromJson(Constants.USERS_JSON_PATH);
        UserDB deletedUsers = loadSaveUsers.loadUsersFromJson(Constants.DELETEDUSERS_JSON_PATH);
        SongDB songDB = loadSaveSongs.loadSongFromJson(Constants.SONG_JSON_PATH);

        SignMenuController signMenuController = new SignMenuController(userDB, deletedUsers, songDB);
        SignUpMenu signUpMenu = new SignUpMenu(signMenuController);


        System.out.println("Login/Register");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Login" -> signUpMenu.login();
            case "Register" -> signUpMenu.register();
            default -> System.out.println("Wrong input");
        }

        loadSaveUsers.saveUsersToJson(Constants.USERS_JSON_PATH, userDB);
        loadSaveUsers.saveUsersToJson(Constants.DELETEDUSERS_JSON_PATH, deletedUsers);
        loadSaveSongs.saveSongsToJson(Constants.SONG_JSON_PATH, songDB);
    }
}
