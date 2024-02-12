package main;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.stream.Stream;


public class DirectoryPlaylistMaker {
    public List<String> paths = new ArrayList<>();

    public int current = 0;
    public int amount = 0;

    public void loadDir(String Path2) {
        if (System.getProperty("os.name").contains("Windows")) {
            //File dir = new File(Path2);
            //parseDirWindows(dir.listFiles());
            parseDirLinux(Path2);
        } else {
            parseDirLinux(Path2);
        }
        amount = paths.size()-1;
    }

    public void parseDirWindows(File[] files) {
        for (File file : files) {
            if (file.isDirectory()) {
                parseDirWindows(file.listFiles());
            } else {
                paths.add(file.getAbsolutePath());
            }
        }
    }
    public void parseDirLinux(String dir) {
        try {
            try (Stream<File> files = Files.list(Paths.get(dir)).map(Path::toFile)) {
                files.forEach(file -> {
                    if (file.isDirectory()) {
                        parseDirLinux(file.getAbsolutePath());
                    } else {
                        paths.add(file.getAbsolutePath());
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
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
