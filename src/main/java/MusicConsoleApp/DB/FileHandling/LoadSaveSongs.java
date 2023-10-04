package MusicConsoleApp.DB.FileHandling;

import MusicConsoleApp.DB.SongData;
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
    String fileNameError;
    Scanner scanner = new Scanner(System.in);

    public SongData loadFromFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            SongData songData = gson.fromJson(fileReader, SongData.class);
            return songData;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            logger.error("Failed to load songs from file {}", filePath);
            fileNameError = scanner.nextLine();
            SongData newSongData;
            newSongData = loadFromFile(fileNameError);
            return newSongData;
        }
    }

    public void saveSongs(String filePath, SongData songData) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            gson.toJson(songData, fileWriter);
        } catch (IOException e) {
            logger.error("Failed to save in file {}", filePath);
            saveSongs(fileNameError, songData);
        }
    }
}
