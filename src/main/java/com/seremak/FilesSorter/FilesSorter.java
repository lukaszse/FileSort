package com.seremak.FilesSorter;

import java.io.*;
import java.nio.file.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class FilesSorter {

    public static final String DEV = "dev";
    public static final String HOME = "home";
    public static final String TEST = "test";

    Path devPath;
    Path homePath;
    Path testPath;

    public FilesSorter() {
        this.devPath = Paths.get(DEV);
        this.homePath = Paths.get(HOME);
        this.testPath = Paths.get(TEST);
    }

    public void createFolders() {
        try {
            Files.createDirectory(devPath);
            Files.createDirectory(homePath);
            Files.createDirectory(testPath);
        } catch (IOException e) {
            System.out.println("Some errors occurred. Not all folders was created. Check if folders already exist");
            // todo error handling could be added here. Also More advance logging could be implemented i.a. Logback [ log.error("Description. Cause {}), e.message())) ]
            return;
        }
        System.out.println("Folders are created");
    }

    public void sortFiles() {
        List<String> fileList = getFilesPathsList();
        fileList.forEach(this::copyToAppropriateFolder);
    }

    private List<String> getFilesPathsList() {
        try {
            return Files.list(Paths.get(FilesSorter.HOME))
                    .map(Path::toString)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error while getting files paths occurred. Error: " + e.getMessage());
            // todo error handling could be added here. Also More advance logging could be implemented i.a. Logback [ log.error("Description. Cause {}, StackTrace), e.message(), e.printStackTrace)) ]
        }
        return Collections.emptyList();
    }

    private void copyToAppropriateFolder(final String filePath) {
        File file = new File(filePath);
        Path originalPath = Paths.get(filePath);
        Date lastModified = new Date(file.lastModified());

        try {
            if (filePath.contains(".xml")) {
                Files.move(originalPath, prepareNewPath(DEV, filePath), StandardCopyOption.REPLACE_EXISTING);
            } else if(filePath.contains(".jar") && lastModified.getHours()%2 == 0) { // todo getHours is deprecated ad should be replaced by other solution
                Files.move(originalPath, prepareNewPath(DEV, filePath), StandardCopyOption.REPLACE_EXISTING);
            } else if(filePath.contains(".jar") && lastModified.getHours()%2 != 0) { // todo getHours is deprecated ad should be replaced by other solution
                Files.move(originalPath, prepareNewPath(TEST, filePath), StandardCopyOption.REPLACE_EXISTING);
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
}
