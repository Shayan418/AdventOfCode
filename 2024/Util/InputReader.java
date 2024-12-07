package Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InputReader {

    public static String[] ReadFile(String path) throws IOException {
        String data = Files.readString(Path.of(path));
        return data.split("\n");
    }
}
