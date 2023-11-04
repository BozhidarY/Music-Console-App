package MusicConsoleApp.DB.FileHandling;

import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Users;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class LoadSaveUsers {
    private static final Logger logger = (Logger) LogManager.getLogger(LoadSaveUsers.class);
    private Gson gson;
    Scanner scanner = new Scanner(System.in);

    public LoadSaveUsers() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Users.class, new UserDeserializer())
                .setPrettyPrinting()
                .create();
    }

    public UserDB loadUsersFromJson(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            Type typeToken = new TypeToken<UserDB>() {}.getType();
            logger.info("Users have beed loaded from {}", filePath);
            return gson.fromJson(fileReader, typeToken);
        } catch (IOException e) {
            logger.error("Failed to load from file {}", filePath);
            System.out.println("THe filePath you provided is wrong. Fix it");
            filePath = scanner.nextLine();
            UserDB newUserDB;
            newUserDB = loadUsersFromJson(filePath);
            return newUserDB;
        }
    }

    public void saveUsersToJson(String filePath, UserDB userDB) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(userDB, fileWriter);
            logger.info("Users have beed succesfully saved in {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save in file {}", filePath);
            System.out.println("THe filePath you provided is wrong. Fix it");
            filePath = scanner.nextLine();
            saveUsersToJson(filePath, userDB);
        }
    }


}
