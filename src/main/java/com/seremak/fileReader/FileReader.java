package com.seremak.fileReader;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    private static List<String> inputStreamToStringList(final String filePath) throws IOException {
        try {
            return new BufferedReader(new InputStreamReader(new FileInputStream(filePath) , StandardCharsets.UTF_8))
                    .lines()
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
