package MusicConsoleApp.Exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
    public static final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]{3,10}$");
    public static final Pattern passwordPattern = Pattern.compile("[0-9]{3,10}$");

    public boolean validateUsername(String username) {
        Matcher matcher = usernamePattern.matcher(username);
        return matcher.matches();
    }

    public boolean validatePassword(String password) {
        Matcher matcher = passwordPattern.matcher(password);
        return matcher.matches();
    }
}
