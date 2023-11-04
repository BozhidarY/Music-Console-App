package MusicConsoleApp.Controller;


import MusicConsoleApp.DB.SongDB;
import MusicConsoleApp.DB.UserDB;
import MusicConsoleApp.Models.Artist;
import MusicConsoleApp.Models.Songs;
import MusicConsoleApp.Models.Users;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistController {
    private Artist artist;
    private UserDB userDB;
    private SongDB songDB;

    public ArtistController(Artist artist, UserDB userDB, SongDB songDB) {
        this.artist = artist;
        this.userDB = userDB;
        this.songDB = songDB;
    }

    public boolean checkIfSongExists(String songName) {
        for (Songs song : songDB.getSongsList()) {
            if (song.getName().equals(songName)) {
                return true;
            }
        }
        return false;
    }

    public void addSongToJsonFile(String songName) {
        Songs newSong = new Songs(songName, artist);
        songDB.getSongsList().add(newSong);
    }

    public List<Artist> showMostListenedArtists() {
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
