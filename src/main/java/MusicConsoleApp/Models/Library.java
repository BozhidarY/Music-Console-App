package MusicConsoleApp.Models;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Playlist> libraryPlaylists;

    private String libraryName;

    public Library(String libraryName) {
        this.libraryPlaylists = new ArrayList<>();
        this.libraryName = libraryName;
    }

    public List<Playlist> getLibraryList() {
        return libraryPlaylists;
    }

    public void setLibraryList(List<Playlist> libraryList) {
        this.libraryPlaylists = libraryList;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
