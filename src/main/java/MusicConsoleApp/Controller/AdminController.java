package MusicConsoleApp.Controller;

import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Admin;
import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private Admin admin;
    private UserDB userDB;
    private UserDB deletedUsers;

    public AdminController(Admin admin, UserDB userDB, UserDB deletedUsers) {
        this.admin = admin;
        this.userDB = userDB;
        this.deletedUsers = deletedUsers;
    }


    public boolean dublicationCheck(String userName) {
        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteAccount(String userName) {
        List<Users> removedAccounts = new ArrayList<>();
        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(userName)) {
                if (!dublicationCheck(userName)) {
                    return false;
                } else {
                    deletedUsers.getUsersList().add(user);
                    removedAccounts.add(user);
                }
            }
        }
        if (removedAccounts.isEmpty()) {
            return false;
        } else {
            userDB.getUsersList().removeAll(removedAccounts);
            return true;
        }

    }

    public boolean recoverAccount(String nameAcc) {
        for (Users user : deletedUsers.getUsersList()) {
            if (nameAcc.equals(user.getUsername()) && dublicationCheck(nameAcc)) {
                userDB.getUsersList().add(user);
                deletedUsers.getUsersList().remove(user);
                return true;
            }
        }
        return false;
    }

    public Admin getAdmin() {
        return admin;
    }

}
