package main;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;


public class ConfigReader {

    public Config parseConfig() {
        String content = "";
        try {
            File file = new File("src/main/java/main/Config.json");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null)
                content = content.concat(st);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        Config cfg;

        try {
            cfg = mapper.readValue(content, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return cfg;
    }
}
