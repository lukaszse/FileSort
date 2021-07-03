package com.seremak;

import com.seremak.fileWriter.FileWriter;
import com.seremak.filesSorter.FilesSorter;
import io.micronaut.configuration.picocli.PicocliRunner;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.io.File;

@Slf4j
@Command(name = "FilesSort", description = "...",
        mixinStandardHelpOptions = true)
public class FilesSortCommand implements Runnable {

    @Inject
    FileWriter fileWriter;

    @Option(names = {"-p", "--path"}, description = "Set path. Current Path is default")
    String path;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(FilesSortCommand.class, args);
    }

    public void run() {
        String currentPath = new File("").getAbsolutePath();
        if (path == null) {
            path = "";
            log.info("Starting sorting files in default path: {}", currentPath);
        } else {
            log.info("Starting sorting files in path: {}", path);
        }

        FilesSorter filesSorter = new FilesSorter(path);
        filesSorter.createFolders();
        filesSorter.sortFiles();
        fileWriter.writeSingleReport(filesSorter.getMovedFilesCounter(), FilesSorter.HOME_FOLDER);
        fileWriter.writeSummaryReport(FilesSorter.HOME_FOLDER);
        log.info("Sorting completed.");
    }
}
