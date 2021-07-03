package com.seremak;

import com.seremak.FilesSorter.FilesSorter;
import io.micronaut.configuration.picocli.PicocliRunner;
import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.File;

@Slf4j
@Command(name = "FilesSort", description = "...",
        mixinStandardHelpOptions = true)
public class FilesSortCommand implements Runnable {

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
    }
}
