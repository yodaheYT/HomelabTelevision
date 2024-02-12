package main;

public class Main {
    public static String DefaultPath = "//clever.local/ServerFiles/08_Vid";
    public static DirectoryPlaylistMaker playlistMaker = new DirectoryPlaylistMaker();
    public static void main(String[] args) {
        if (args.length > 0) {
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