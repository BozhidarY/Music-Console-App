package MusicConsoleApp.DB.FileHandling;

import MusicConsoleApp.DB.SongDB;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class LoadSaveSongs {
    private static final Logger logger = (Logger) LogManager.getLogger(LoadSaveSongs.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Scanner scanner = new Scanner(System.in);

    public SongDB loadSongFromJson(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            SongDB songDB = gson.fromJson(fileReader, SongDB.class);
            return songDB;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error("Failed to load songs from file {}", filePath);
            filePath = scanner.nextLine();
            SongDB newSongDB;
            newSongDB = loadSongFromJson(filePath);
            return newSongDB;
        }
    }

    public void saveSongsToJson(String filePath, SongDB songDB) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(songDB, fileWriter);
        } catch (IOException e) {
            logger.error("Failed to save in file {}", filePath);
            filePath = scanner.nextLine();
            saveSongsToJson(filePath, songDB);
        }
    }
}
