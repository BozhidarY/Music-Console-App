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


    public boolean isDublicateUsername(String userName) {
        for (Users user : deletedUsers.getUsersList()) {
            if (user.getUsername().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public boolean isDublicateUsernameDel(String userName) {
        for (Users user : userDB.getUsersList()) {
            if (user.getUsername().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public boolean deleteUserAccount(String userName) {
        List<Users> removedAccounts = new ArrayList<>();
        for (Users user : userDB.getUsersList()) {
            System.out.println(user);
            if (user.getUsername().equals(userName)) {
                if (!isDublicateUsername(userName)) {
                    return false;
                } else {
                    deletedUsers.getUsersList().add(user);
                    removedAccounts.add(user);
                }
            }
        }
        userDB.getUsersList().removeAll(removedAccounts);
        return true;
    }

    public boolean recoverUserAccount(String nameAcc) {
        for (Users user : deletedUsers.getUsersList()) {
            if (nameAcc.equals(user.getUsername()) && isDublicateUsernameDel(nameAcc)) {
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
