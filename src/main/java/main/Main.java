package main;

public class Main {

    static ConfigReader configReader = new ConfigReader();
    public static DirectoryPlaylistMaker playlistMaker = new DirectoryPlaylistMaker();
    public static void main(String[] args) {
        Config cfg = configReader.parseConfig();
        if (args.length > 0) {
            playlistMaker.loadDir(args[0]);
        } else {
            playlistMaker.loadDir(cfg.dirToParse);
        }
        if (cfg.randomize) {
            playlistMaker.randomize();
        }

        Display display = new Display(playlistMaker);
        display.init();
        display.loadVideo(playlistMaker.getCurrent());
    }
}