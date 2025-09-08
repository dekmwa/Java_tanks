package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParsing {
    public int[][] readFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));

            int lines = 0;
            int columns = 0;
            while (scanner.hasNextLine()) {
                columns = scanner.nextLine().split(" ").length;
                lines++;
            }

            scanner.close();
            int[][] massive = new int[lines][columns];

            scanner = new Scanner(new File(path));
            for (int i = 0; i < lines; i++) {
                for (int j = 0; j < columns; j++) {
                    if (scanner.hasNextInt()) {
                        massive[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
            return massive;
        } catch (FileNotFoundException e) {
            return null;
        }
    }
}
