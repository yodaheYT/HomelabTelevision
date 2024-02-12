package main;

public class Main {
    public static String DefaultPath = "";
    public static DirectoryPlaylistMaker playlistMaker = new DirectoryPlaylistMaker();
    public static void main(String[] args) {
        if (args[0] != null) {
            playlistMaker.loadDir(args[0]);
        } else {
            playlistMaker.loadDir(DefaultPath);
        }
        playlistMaker.randomize();

        Display display = new Display(playlistMaker);
        display.init();
        display.loadVideo(playlistMaker.getCurrent());
    }
}