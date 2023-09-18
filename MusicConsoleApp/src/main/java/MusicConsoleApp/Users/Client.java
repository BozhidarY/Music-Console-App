package MusicConsoleApp.Users;

import MusicConsoleApp.Librarys.Library;
import MusicConsoleApp.Librarys.Playlists;

public class Client extends Users {

    private Library library;
    private Playlists playlists;

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

    @Override
    public String toString() {
        return "Client{" +
                "library=" + library +
                ", playlists=" + playlists +
                '}';
    }
}
