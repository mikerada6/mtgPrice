package com.rezatron.mtgprice.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@AllArgsConstructor
@Slf4j
public
class FileService {

    public
    boolean doesFileExist(String fileName)
    {
        log.info("Checking to see if {} exits.",
                 fileName);
        File f = new File(fileName);
        if (f.exists()) {
            log.info("{} does exist.",
                     fileName);
            return true;
        } else {
            log.info("{} does not exist.",
                     fileName);
            return false;
        }
    }

    public
    String loadFile(String file) throws IOException
    {
        log.info("Loading file {}.",
                 file);
        try {
            return Files.readString(Path.of(file));
        } catch (IOException e) {
            log.error("Could not load file {}.",
                      file);
            throw e;
        }
    }


    public
    void saveFile(String fileName, String fileContents) throws IOException
    {
        log.info("Trying to save file {}.",
                 fileName);
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(fileContents);
            myWriter.close();
            log.info("Successfully wrote to the file.");
        } catch (IOException e) {
            log.error("An error occurred while trying to write the file: {}.",
                      e);
            throw e;
        }
    }

}
