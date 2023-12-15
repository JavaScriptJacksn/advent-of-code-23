package advent.code.solutions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public abstract class Solution {
    public abstract void run();


    protected List<String> readInputFile(String filePath){
        Path path = Paths.get(filePath);
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("ERROR: " + e);
        }
        return Collections.emptyList();
    }


}
