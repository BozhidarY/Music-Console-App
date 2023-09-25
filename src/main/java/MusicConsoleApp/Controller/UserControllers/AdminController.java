package MusicConsoleApp.Controller.UserControllers;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Users;
import MusicConsoleApp.View.AdminView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController {
    private Admin admin;
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();
    Scanner scanner = new Scanner(System.in);

    public AdminController(Admin admin){
        this.admin = admin;
    }

    public void deleteAccount(UserDB userDB, String userName) {
        UserDB userDB1 = new UserDB();
        List<Users> removedAccounts = new ArrayList<>();
        userDB.getUsersList().forEach(user -> {
            if (userName.equals(user.getUsername())) {
                removedAccounts.add(user);
                userDB1.getUsersList().add(user);

            }
        });
        userDB.getUsersList().removeAll(removedAccounts);
        loadSaveUsersToJson.saveUsers(Constants.DELETEDUSERS_JSON_PATH, userDB1);
    }

    public void recoverAccount(UserDB userDB, String nameAcc) {
        UserDB userDB1;
        userDB1 = loadSaveUsersToJson.loadUsers(Constants.DELETEDUSERS_JSON_PATH);
        for (Users user : userDB1.getUsersList()) {
            if (nameAcc.equals(user.getUsername())) {
                userDB.getUsersList().add(user);
            }
        }
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
