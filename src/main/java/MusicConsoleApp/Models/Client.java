package MusicConsoleApp.Models;

import java.util.HashMap;

public class Client extends Users {

    private Library library;
    private HashMap<String, Integer> favouriteArtist;

    public Client(String username, String password) {
        super(username, password);
        setUserType(UserType.CLIENT);
        this.library = new Library(getUsername() + " library");
        library.getLibraryList().add(new Playlist("defaultPlaylist"));
    }


    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public String toString() {
        return "Client{" +
                "library=" + library +
                '}';
    }
}
