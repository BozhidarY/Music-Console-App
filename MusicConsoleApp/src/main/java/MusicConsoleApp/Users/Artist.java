package MusicConsoleApp.Users;

public class Artist extends Users {

    private long monthlyListeners = 0;

    //    private List<Songs> songsList;
    public Artist(String username, String password) {
        super(username, password);
//        this.songsList = new ArrayList<>();
        setUserType(UserType.ARTIST);
    }

    public void listenToSong() {
        this.monthlyListeners++;
    }

//    public List<Songs> getSongsList() {
//        return songsList;
//    }
//
//    public void setSongsList(List<Songs> songsList) {
//        this.songsList = songsList;
//    }

    public long getMonthlyListeners() {
        return monthlyListeners;
    }

    public void setMonthlyListeners(long monthlyListeners) {
        this.monthlyListeners = monthlyListeners;
    }
}
