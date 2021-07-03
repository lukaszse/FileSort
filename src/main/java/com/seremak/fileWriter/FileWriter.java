package com.seremak.fileWriter;

import com.seremak.fileReader.FileReader;
import com.seremak.filesSorter.FilesSorter;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class FileWriter {

    private static final String REPORT_FILE_NAME = "count";
    private static final String SUMMARY = "summary";
    private Map<String, Integer> totalMovedFilesCounter;

    FileWriter() {
        this.totalMovedFilesCounter = FilesSorter.prepareFileCounter();
    }

    public void writeReport(final Map<String, Integer> movedFilesQty, final String path, final String suffix) {
        String filePath = String.format("%s/%s_%s.txt", path, REPORT_FILE_NAME, suffix);
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

    public void writeSingleReport(final Map<String, Integer> movedFilesQty, final String path) {
        Date currentDate = Date.from(Instant.now());
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd_HHmmss");
        String formattedDate = formatter.format(currentDate);

        writeReport(movedFilesQty, path, formattedDate);
    }

    private String prepareCurrentDate() {
        return new SimpleDateFormat("yyMMdd_HHmmss").format(Date.from(Instant.now()));
    }

    public void writeSummaryReport(String path) {
        List<String> fileList = new ArrayList<>();
        try {
            fileList = Files.list(Paths.get(path))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.info("Error occurred while fetching the list of files. Path: {}", path);
        }
        List<String> countReportsList = fileList.stream()
                .filter(fileName -> fileName.contains("count"))
                .filter(fileName -> fileName.contains(".txt"))
                .filter(fileName -> !fileName.contains("summary"))  // todo simplify to regex
                .collect(Collectors.toList());

        countReportsList.forEach(fileName -> {
            List<String> fileInListOfLines = FileReader.readFileToList(fileName);
            readFileAndUpdateCounter(fileInListOfLines);
        });

        writeReport(totalMovedFilesCounter, path, SUMMARY);
    }

    private void readFileAndUpdateCounter(final List<String> fileInStringOfLines) {
        List<String> lineTotal = Arrays.asList(fileInStringOfLines.get(2).split(": "));
        int currentTotalQty = totalMovedFilesCounter.get(FilesSorter.TOTAL_QTY) + Integer.parseInt(lineTotal.get(1));
        List<String> lineDev = Arrays.asList(fileInStringOfLines.get(3).split(": "));
        int currentDevQty = totalMovedFilesCounter.get(FilesSorter.DEV_FOLDER) + Integer.parseInt(lineDev.get(1));
        List<String> lineTest = Arrays.asList(fileInStringOfLines.get(4).split(": "));
        int currentTestQty = totalMovedFilesCounter.get(FilesSorter.TEST_FOLDER) + Integer.parseInt(lineTest.get(1));

        totalMovedFilesCounter.replace(FilesSorter.TOTAL_QTY, currentTotalQty);
        totalMovedFilesCounter.replace(FilesSorter.DEV_FOLDER, currentDevQty);
        totalMovedFilesCounter.replace(FilesSorter.TEST_FOLDER, currentTestQty);
    }

}
