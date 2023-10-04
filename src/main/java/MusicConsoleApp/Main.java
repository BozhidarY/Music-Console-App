package MusicConsoleApp;


import MusicConsoleApp.Controller.SignMenuController;
import MusicConsoleApp.DB.FileHandling.LoadSaveUsers;

import MusicConsoleApp.DB.FileHandling.LoadSaveSongs;
import MusicConsoleApp.DB.SongData;
import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.View.SignUpMenu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        UserDB userDB;
        UserDB deletedUsers;
        SongData songData;
        LoadSaveUsers loadSaveUsers = new LoadSaveUsers();
        LoadSaveSongs loadSaveSongs = new LoadSaveSongs();

        userDB = loadSaveUsers.loadUsers(Constants.USERS_JSON_PATH);
        deletedUsers = loadSaveUsers.loadUsers(Constants.DELETEDUSERS_JSON_PATH);
        songData = loadSaveSongs.loadFromFile(Constants.SONG_JSON_PATH);

        SignMenuController signMenuController = new SignMenuController(userDB, deletedUsers, songData);
        SignUpMenu signUpMenu = new SignUpMenu(signMenuController);

        System.out.println("Login/Register");
        String choice = scanner.nextLine();
        switch (choice) {
            case "Login" -> signUpMenu.login();
            case "Register" -> signUpMenu.register();
            default -> System.out.println("Wrong input");
        }

        loadSaveUsers.saveUsers(Constants.USERS_JSON_PATH, userDB);
        loadSaveUsers.saveUsers(Constants.DELETEDUSERS_JSON_PATH, deletedUsers);
        loadSaveSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
    }
}
