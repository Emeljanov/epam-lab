package com.epam.winterjavalab.service;

import com.epam.winterjavalab.beans.Settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileService {
    private final static String EXT_FILE = ".json";
    private final static String START_NAME_FILE = "db_";
    private final static String PATTERN_FILE = "[A-Za-z0-9]*";

    public FileService() {
    }
    public List<String> getTransactionsFilesList (String dirPath, Settings settings) {
        List<String> fileNameList = new ArrayList<>();

        if(settings.getUseDepartsment() == null) {
            fileNameList = getAllDepartments(dirPath);
            return fileNameList;

        }
        else if(settings.getUseDepartsment().size() == 0) {
            return fileNameList;
        }
        else {
            List<String> result = getSettingDepartments(settings);
            return result;
        }


    }
    private List<String> getAllDepartments(String dirPath) {

        List<String> fileList = new ArrayList<>();

        if(!(Files.exists(Paths.get(dirPath)))){
            throw new RuntimeException("Wrong direction");
        }
        else  {
            try(Stream<Path> paths = Files.walk(Paths.get(dirPath))) {
                fileList = paths.filter(Files::isRegularFile)
                        .map(x -> x.getFileName().toString())
                        .filter(x -> x.endsWith(EXT_FILE))
                        .filter(x -> x.startsWith(START_NAME_FILE))
                        .filter(x -> x.substring(START_NAME_FILE.length(), x.length() - EXT_FILE.length())
                                .matches(PATTERN_FILE))
                        .collect(Collectors.toCollection(ArrayList::new));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  fileList;
    }
    private List<String> getSettingDepartments(Settings settings) {
        List<String> departsmentsNames = settings.getUseDepartsment();
        List<String> resultList = new ArrayList<>();

        for(String depName : departsmentsNames) {
            resultList.add(START_NAME_FILE + depName + EXT_FILE);
        }

        return resultList;


    }
}
