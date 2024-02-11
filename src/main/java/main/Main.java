package main;

public class Main {
    public static DirectoryPlaylistMaker playlistMaker = new DirectoryPlaylistMaker();
    public static void main(String[] args) {
        playlistMaker.loadDir("\\\\clever.local\\Server Files\\08. Vid");
        playlistMaker.randomize();

        Display display = new Display(playlistMaker);
        display.init();
        display.loadVideo(playlistMaker.getCurrent());
    }
}