package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.UserControllers.ArtistController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Artist;

import java.util.List;
import java.util.Scanner;

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
        if(!artistController.checkIfSongExists(songName)){
            artistController.addSongToJson(songName);
        }
        else {
            System.out.println("Song already exists.");
        }

    }


}
