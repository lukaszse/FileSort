package com.seremak.fileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {

    public static List<String> readFileToList(String fileName) {
        List<String> iscasCodeRowList = new ArrayList<>();
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                iscasCodeRowList.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return iscasCodeRowList;
    }
}
