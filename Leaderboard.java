import java.io.*;
import java.util.*;

public class Leaderboard {
    private static final String FILE = "leaderboard.csv";

    public static void save(String name, int time) {
        try {
            FileWriter fw = new FileWriter(FILE, true);
            fw.write(name + "," + time + "\n");
            fw.close();
        } catch (IOException e) { e.printStackTrace(); }
    }
}
