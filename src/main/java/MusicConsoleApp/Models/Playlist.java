package MusicConsoleApp.Models;

import java.util.ArrayList;
import java.util.List;

public class Playlist {

    private String playlistName;
    private List<Songs> songPlaylist;

    public Playlist(String playlistName) {
        this.playlistName = playlistName;
        this.songPlaylist = new ArrayList<>();
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public List<Songs> getSongPlaylist() {
        return songPlaylist;
    }

    public void setSongPlaylist(List<Songs> songPlaylist) {
        this.songPlaylist = songPlaylist;
    }
}
