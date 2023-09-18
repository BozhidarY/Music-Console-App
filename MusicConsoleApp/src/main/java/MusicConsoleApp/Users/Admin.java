package MusicConsoleApp.Users;

public class Admin extends Users {

    private final static Admin admin = new Admin("admin", "admin");

    private Admin(String username, String password) {
        super(username, password);
        setUserType(UserType.ADMIN);
    }

    public static Admin getAdmin() {
        return admin;
    }

}
