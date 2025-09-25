package utils;

import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import exception.InvalidDataFormatException;


public class FileParsing {
    public int[][] parseFile(String filePath, int numbersCnt) throws FileNotFoundException, InvalidDataFormatException {

        Scanner fileScanner1 = new Scanner(new File(filePath));
        int fileLinesCnt = checkFile(fileScanner1, numbersCnt);
        fileScanner1.close();

        Scanner fileScanner2 = new Scanner(new File(filePath));
        int[][] parsedFileArray = fillArray(fileScanner2, fileLinesCnt, numbersCnt);
        fileScanner2.close();
        return parsedFileArray;
    }

    private int checkFile(Scanner fileScanner, int numbersCnt) throws InvalidDataFormatException {
        int linesCnt = 0;

        while(fileScanner.hasNextLine()) {
            if (fileScanner.nextLine().split(" ").length == numbersCnt) {
                linesCnt++;
            } else {
                throw new InvalidDataFormatException();
            }
        }
        
        return linesCnt;
    }

    private int[][] fillArray(Scanner fileScanner, int linesCnt, int numbersCnt) {
        int[][] arr = new int[linesCnt][numbersCnt];

        for (int i = 0; i < linesCnt; i++) {
            for (int j = 0; j < numbersCnt; j++) {
                arr[i][j] = fileScanner.nextInt();
            }
        }

        return arr;
    }
}
