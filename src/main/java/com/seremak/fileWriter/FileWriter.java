package com.seremak.fileWriter;

import com.seremak.filesSorter.FilesSorter;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

@Singleton
public class FileWriter {

    private static final String REPORT_FILE_NAME = "count";
    public void writeReport(Map<String, Integer> movedFilesQty, String path) {
        Date currentDate = Date.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd_HHmmss");
        String formattedDate = formatter.format(currentDate);

        String filePath = String.format("%s/%s_%s.txt", path, REPORT_FILE_NAME, prepareCurrentDate());
        try (PrintWriter printWriter = new PrintWriter(new File(filePath))){
            printWriter.println("--------------------------------------------------------");
            printWriter.println("Sorting report. Date: " + Instant.now().toString());
            printWriter.println(String.format("total: %d", movedFilesQty.get(FilesSorter.TOTAL_QTY)));
            printWriter.println(String.format("dev: %d", movedFilesQty.get(FilesSorter.DEV_FOLDER)));
            printWriter.println(String.format("test: %d", movedFilesQty.get(FilesSorter.TEST_FOLDER)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String prepareCurrentDate() {
        return new SimpleDateFormat("yyMMdd_HHmmss").format(Date.from(Instant.now()));
    }
}
