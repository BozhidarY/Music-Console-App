package MusicConsoleApp.Librarys;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Playlists> libraryPlaylists;

    private String libraryName;

    public Library(String libraryName) {
        this.libraryPlaylists = new ArrayList<>();
        this.libraryName = libraryName;
    }

    public List<Playlists> getLibraryList() {
        return libraryPlaylists;
    }

    public void setLibraryList(List<Playlists> libraryList) {
        this.libraryPlaylists = libraryList;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }
}
