package main;

import java.io.File;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Objects;

public class DirectoryPlaylistMaker {
    public File dir;
    public List<String> paths = new ArrayList<>();

    public int current = 0;
    public int amount = 0;

    public void loadDir(String Path) {
        dir = new File(Path);
        parseDir(Objects.requireNonNull(dir.listFiles()));

        amount = paths.size()-1;
    }

    public void parseDir(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                parseDir(Objects.requireNonNull(file.listFiles()));
            } else {
                paths.add(file.getAbsolutePath());
            }
        }
    }

    public void randomize() {
        Collections.shuffle(paths);
    }

    public String getCurrent() {
        return paths.get(current);
    }
    public void skip() {
        if (current+1 <= amount) {
            current += 1;
        }

        System.out.println("Skipping");
        System.out.println(current);
        System.out.println(amount);
    }
    public void previous() {
        if (current+1 >= 0) {
            current -= 1;
        }

        System.out.println("Previous");
        System.out.println(current);
        System.out.println(amount);    }
}
