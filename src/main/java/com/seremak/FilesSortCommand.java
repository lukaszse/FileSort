package com.seremak;

import com.seremak.FilesSorter.FilesSorter;
import io.micronaut.configuration.picocli.PicocliRunner;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "FilesSort", description = "...",
        mixinStandardHelpOptions = true)
public class FilesSortCommand implements Runnable {

//      todo implement support for various pahts
//    @Option(names = {"-p", "--path"}, description = "Set path. Current Path is default")
//    String path;

    public static void main(String[] args) throws Exception {
        PicocliRunner.run(FilesSortCommand.class, args);
    }

    public void run() {
        FilesSorter filesSorter = new FilesSorter();
        filesSorter.createFolders();
        filesSorter.sortFiles();

//      todo implement support for various pahts
//        if (path == null) {
//            path = "";
//            System.out.println("Current path ws taken by default");
//        }
    }
}
