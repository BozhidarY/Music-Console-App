package MusicConsoleApp.Models;

import java.util.HashMap;

public class Client extends Users {

    private Library library;
    private Playlists playlists;

    private HashMap<String, Integer> favouriteArtist;

    public Client(String username, String password) {
        super(username, password);
        setUserType(UserType.CLIENT);
        this.library = new Library(getUsername() + " library");
        library.getLibraryList().add(new Playlists("defaultPlaylist"));
    }


    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public Playlists getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Playlists playlists) {
        this.playlists = playlists;
    }

    public HashMap<String, Integer> getFavouriteArtist() {
        return favouriteArtist;
    }

    public void setFavouriteArtist(HashMap<String, Integer> favouriteArtist) {
        this.favouriteArtist = favouriteArtist;
    }

    @Override
    public String toString() {
        return "Client{" +
                "library=" + library +
                ", playlists=" + playlists +
                '}';
    }
}
