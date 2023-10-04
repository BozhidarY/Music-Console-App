package MusicConsoleApp.View;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.UserControllers.ArtistController;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Artist;

import java.util.List;
import java.util.Scanner;

public class ArtistView {
    Scanner scanner = new Scanner(System.in);
    private ArtistController artistController;

    public ArtistView(ArtistController artistController){
        this.artistController = artistController;
    }

    public void openArtistCommunication() {

        System.out.println("Show artist chart?");
        String choice = scanner.nextLine();
        if(choice.equals("Y")){
            List<Artist> artistList = artistController.showMostListened();
            for (Artist artist : artistList) {
                System.out.println("Artist: " + artist.getUsername());
                System.out.println("Total Views: " + artist.getTotalViews());
            }
        }

        System.out.println("As an artist you add songs to your profile");
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
