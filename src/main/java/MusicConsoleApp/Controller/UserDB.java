package MusicConsoleApp.Controller;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private List<Users> usersList;

    public UserDB() {
        this.usersList = new ArrayList<>();
    }



    public List<Users> getUsersList() {
        return usersList;
    }



}
