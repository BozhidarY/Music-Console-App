package MusicConsoleApp.Controller;


import MusicConsoleApp.DB.SongData;
import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Songs;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistController {
    private Artist artist;
    private UserDB userDB;
    private SongData songData;

    public ArtistController(Artist artist, UserDB userDB, SongData songData) {
        this.artist = artist;
        this.userDB = userDB;
        this.songData = songData;
    }

    public boolean checkIfSongExists(String songName) {
        for (Songs song : songData.getSongsList()) {
            if (song.getName().equals(songName)) {
                return true;
            }
        }
        return false;
    }

    public void addSongToJson(String songName) {
        Songs newSong = new Songs(songName, artist);
        songData.getSongsList().add(newSong);
    }

    public void printArtistSongs(Artist artist) {
        for (Songs song : songData.getSongsList()) {
            if (song.getArtistName().equals(artist.getUsername())) {
                System.out.println("Your songs: ");
                System.out.println(song);
            }
        }
    }

    public List<Artist> showMostListened() {
        List<Artist> artistList = userDB.getUsersList().stream()
                .filter(users -> users instanceof Artist)
                .map(users -> (Artist) users)
                .collect(Collectors.toList());

        Comparator<Artist> artistListenerComparator = Comparator.comparingLong(Artist::getTotalViews).reversed();
        artistList.sort(artistListenerComparator);
        return artistList;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
