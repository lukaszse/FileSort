package com.seremak;

import com.seremak.SortFiles.SortFiles;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        SortFiles sortFiles = new SortFiles();
        sortFiles.createFolders();

        sortFiles.sortFiles();

//        List<String> fileList = sortFiles.getFilesPathsList();
//        System.out.println(fileList);

    }
}
