package MusicConsoleApp.DB;

import MusicConsoleApp.Models.Songs;

import java.util.ArrayList;
import java.util.List;

public class SongDB {
    private final List<Songs> songs;

    public SongDB() {
        this.songs = new ArrayList<>();
    }

    public List<Songs> getSongsList() {
        return songs;
    }

    @Override
    public String toString() {
        return "{" + songs + "}";
    }
}
