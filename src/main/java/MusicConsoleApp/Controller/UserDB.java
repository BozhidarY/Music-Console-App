package MusicConsoleApp.Controller;

import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class UserDB {
    private final List<Users> usersList;

    public UserDB() {
        this.usersList = new ArrayList<>();
    }

    public List<Users> getUsersList() {
        return usersList;
    }

}
