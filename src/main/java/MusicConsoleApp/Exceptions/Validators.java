package MusicConsoleApp.Exceptions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {
    public static final Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]{3,10}$");
    public static final Pattern passwordPattern = Pattern.compile("[0-9]{3,10}$");


    public boolean validateUsername(String username) throws WrongCredentialsException{
            Matcher matcher = usernamePattern.matcher(username);
            if(matcher.matches()){
                return true;
            }
            else {
                throw new WrongCredentialsException();
            }
    }

    public boolean validatePassword(String password) throws WrongCredentialsException{
        Matcher matcher = passwordPattern.matcher(password);
        if(matcher.matches()){
            return true;
        }
        else {
            throw new WrongCredentialsException();
        }
    }
}
