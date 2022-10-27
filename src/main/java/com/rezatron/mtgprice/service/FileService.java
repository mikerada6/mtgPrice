package com.rezatron.mtgprice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public
class FileService {

    @Value( "${mtg.download.baselocation}" )
    private String baseFileLocation;

    public
    boolean doesFileExist(String fileName)
    {
        log.info( "Checking to see if {} exits.",
                  fileName );
        File f = new File( fileName );
        if (f.exists()) {
            log.info( "{} does exist.",
                      fileName );
            return true;
        } else {
            log.info( "{} does not exist.",
                      fileName );
            return false;
        }
    }

    public
    String loadFile(String file) throws IOException
    {
        log.info( "Loading file {}.",
                  file );
        try {
            return Files.readString( Path.of( file ) );
        } catch (IOException e) {
            log.error( "Could not load file {}.",
                       file );
            return null;
        }
    }

    public
    boolean createFolder(String folderPath)
    {
        try {
            File directory = new File( folderPath );
            if (!directory.exists()) {
                directory.mkdirs();
                log.info( "Creating folder {}.",
                          folderPath );
                return true;
            }
            return true;
        } catch (Exception e) {
            log.error( "Could not create the folder path {}.",
                       folderPath );
            return false;
        }
    }


    public
    void saveFile(String fileName, String fileContents) throws IOException
    {
        log.info( "Trying to save file {}.",
                  fileName );
        try {
            FileWriter myWriter = new FileWriter( fileName );
            myWriter.write( fileContents );
            myWriter.close();
            log.info( "Successfully wrote to the file." );
        } catch (IOException e) {
            log.error( "An error occurred while trying to write the file: {}.",
                       e );
            throw e;
        }
    }

    public
    List<String> getAvailableBulkDataFiles() {
        String filePath = baseFileLocation + "/bulkData/";
        File[] files = new File( filePath ).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null.
        List<String> results = new ArrayList<String>();
        for (File file : files) {
            if (file.isFile()) {
                results.add( file.getName() );
            }
        }
        return results.stream().filter( f -> f.contains( ".json" ) ).map( f -> baseFileLocation + "/bulkData/" + f )
                      .collect( Collectors.toList() );
    }
}
