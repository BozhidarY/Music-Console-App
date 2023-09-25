package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSaveUsersToJson;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    Scanner scanner = new Scanner(System.in);
    LoadSaveUsersToJson loadSaveUsersToJson = new LoadSaveUsersToJson();

    public void deleteAccount(Admin admin, UserDB userDB) {
        UserDB userDB1 = new UserDB();
        List<Users> removedAccounts = new ArrayList<>();
        System.out.println("You re logged in as an " + admin.getUserType());
        System.out.println("As an admin you can delete and recover profiles");
        System.out.println("Enter account username you want to remove");
        String userName = scanner.nextLine();
        userDB.getUsersList().forEach(user -> {
            if (userName.equals(user.getUsername())) {
                removedAccounts.add(user);
                userDB1.getUsersList().add(user);

            }
        });
        userDB.getUsersList().removeAll(removedAccounts);
        loadSaveUsersToJson.saveUsers(Constants.DELETEDUSERS_JSON_PATH, userDB1);
    }

    public void recoverAccount(Admin admin, UserDB userDB) {
        UserDB userDB1;
        userDB1 = loadSaveUsersToJson.loadUsers(Constants.DELETEDUSERS_JSON_PATH);
        System.out.println("Enter the name of the account you want to recover");
        String nameAcc = scanner.nextLine();
        for (Users user : userDB1.getUsersList()) {
            if (nameAcc.equals(user.getUsername())) {
                userDB.getUsersList().add(user);
            }
        }
    }
}
