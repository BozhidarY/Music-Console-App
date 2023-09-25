package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserControllers.ArtistController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Songs;
import MusicConsoleApp.Models.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArtistView {
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();

    private ArtistController artistController;

    public ArtistView(ArtistController artistController){
        this.artistController = artistController;
    }

    public void openArtistCommunication(UserDB userDB) {
        System.out.println("As an artist you add songs to your profile");

        List<Artist> artistList = artistController.showMostListened(userDB);
        for (Artist artist : artistList) {
            System.out.println("Artist: " + artist.getUsername());
            System.out.println("Total Views: " + artist.getTotalViews());
        }

        System.out.println("Type the name of the song you want to add");
        String songName = scanner.nextLine();
        artistController.addSongToJson(songName);
    }


}
