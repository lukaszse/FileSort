package com.seremak.filesSorter;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class FilesSorter {

    public static final String DEV_FOLDER = "dev";
    public static final String HOME_FOLDER = "home";
    public static final String TEST_FOLDER = "test";
    public static final String TOTAL_QTY = "total";

    private final Path devPath;
    private final Path homePath;
    private final Path testPath;
    private Map<String, Integer> movedFilesCounter;

    public FilesSorter(final String mainStringPath) {
        this.devPath = Paths.get(mainStringPath, DEV_FOLDER);
        this.homePath = Paths.get(mainStringPath, HOME_FOLDER);
        this.testPath = Paths.get(mainStringPath, TEST_FOLDER);
        this.movedFilesCounter = prepareFileCounter();
    }

    public void createFolders() {
        try {
            Files.createDirectory(devPath);
            Files.createDirectory(homePath);
            Files.createDirectory(testPath);
        } catch (IOException e) {
            log.warn("Some errors occurred. Not all folders was created. Check if folders already exist");
            // todo error handling could be added here. Also More advance logging could be implemented i.a. Logback [ log.error("Description. Cause {}), e.message())) ]
            return;
        }
        log.info("Folders are created");
    }

    public void sortFiles() {
        List<String> fileList = getFilesPathsList();
        fileList.forEach(this::copyToAppropriateFolder);
    }

    private List<String> getFilesPathsList() {
        try {
            return Files.list(homePath)
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            log.error("Error while getting files paths occurred. Error: " + e.getMessage());
            // todo error handling could be added here. Also More advance logging could be implemented i.a. Logback [ log.error("Description. Cause {}, StackTrace), e.message(), e.printStackTrace)) ]
        }
        return Collections.emptyList();
    }

    private void copyToAppropriateFolder(final String filePath) {
        Map<String, Integer> filesCounter;
        File file = new File(filePath);
        Path originalPath = Paths.get(filePath);
        Date lastModified = new Date(file.lastModified());

        try {
            if (filePath.contains(".xml")) {
                Files.move(originalPath, prepareNewPath(DEV_FOLDER, filePath), StandardCopyOption.REPLACE_EXISTING);
                writeSortedFilesQty(DEV_FOLDER);
            } else if(filePath.contains(".jar") && lastModified.getHours()%2 == 0) { // todo getHours is deprecated ad should be replaced by other solution
                Files.move(originalPath, prepareNewPath(DEV_FOLDER, filePath), StandardCopyOption.REPLACE_EXISTING);
                writeSortedFilesQty(DEV_FOLDER);
            } else if(filePath.contains(".jar") && lastModified.getHours()%2 != 0) { // todo getHours is deprecated ad should be replaced by other solution
                Files.move(originalPath, prepareNewPath(TEST_FOLDER, filePath), StandardCopyOption.REPLACE_EXISTING);
                writeSortedFilesQty(TEST_FOLDER);
            }
        } catch (IOException e) {
            // todo error handling to be implemented
            e.printStackTrace();
        }
    }

    Path prepareNewPath(final String path, final String filePath) {
        String fileName = Paths.get(filePath).getFileName().toString();
        return Paths.get(path, fileName);
    }

    void writeSortedFilesQty(String key) {
        Integer counter = movedFilesCounter.get(key);
        Integer counterTotal = movedFilesCounter.get(TOTAL_QTY);
        movedFilesCounter.replace(key, ++counter);
        movedFilesCounter.replace(TOTAL_QTY, ++counterTotal);
    }

    Map<String, Integer> prepareFileCounter() {
        Map<String, Integer> fileCounter = new HashMap<>();
        fileCounter.put(DEV_FOLDER, 0);
        fileCounter.put(TEST_FOLDER, 0);
        fileCounter.put(TOTAL_QTY, 0);
        return fileCounter;
    }

    public Map<String, Integer> getMovedFilesCounter() {
        return movedFilesCounter;
    }
}
