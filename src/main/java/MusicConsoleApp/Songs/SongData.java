package MusicConsoleApp.Songs;

import java.util.ArrayList;
import java.util.List;

public class SongData {
    private final List<Songs> songs;

    public SongData() {
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
