package MusicConsoleApp.View.ServiceMethods;

import MusicConsoleApp.Controller.FileHandling.LoadSongs;
import MusicConsoleApp.Controller.SongData;
import MusicConsoleApp.Controller.UserDB;
import MusicConsoleApp.Models.Constants;
import MusicConsoleApp.Models.Songs;
import MusicConsoleApp.Models.Artist;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ArtistServiceMethods {
    Scanner scanner = new Scanner(System.in);
    LoadSongs loadSongs = new LoadSongs();

    public void addSongToJson(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        System.out.println("As an artist you add songs to your profile");
        System.out.println("Type the name of the song you want to add");
        String songName = scanner.nextLine();
        Songs newSong = new Songs(songName, artist);
        songData.getSongsList().add(newSong);
        loadSongs.saveSongs(Constants.SONG_JSON_PATH, songData);
    }

    public void printArtistSongs(SongData songData, Artist artist) {
        songData = loadSongs.loadFromFile(Constants.SONG_JSON_PATH);
        for (Songs song : songData.getSongsList()) {
            if (song.getArtistName().equals(artist.getUsername())) {
                System.out.println("Your songs: ");
                System.out.println(song);
            }
        }
    }

    public void showMostListened(UserDB userDB){
        List<Artist> artistList = userDB.getUsersList().stream()
                .filter(users -> users instanceof Artist)
                .map(users -> (Artist) users)
                .collect(Collectors.toList());

        Comparator<Artist> artistListenerComparator = Comparator.comparingLong(Artist::getTotalViews).reversed();
        artistList.sort(artistListenerComparator);

        for (Artist artist : artistList) {
            System.out.println("Artist: " + artist.getUsername());
            System.out.println("Total Views: " + artist.getTotalViews());
        }
    }
}
