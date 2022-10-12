package com.rezatron.mtgprice.service;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith( MockitoExtension.class )
class FileServiceTest {
    @InjectMocks
    FileService fileService;

    @BeforeEach
    void init() {
        AutoCloseable closeable = MockitoAnnotations.openMocks( this );
        ReflectionTestUtils.setField( fileService, "baseFileLocation", "src/test/resources/tempFiles/");

    }

    @Test
    void doesFileExist() {
        assertTrue(fileService.doesFileExist( "src/test/resources/twoCard.json" ));
        assertFalse(fileService.doesFileExist( "src/test/resources/badFile.json" ));
    }

    @Test
    void loadFile() throws IOException {
        String file = fileService.loadFile( "src/test/resources/twoCard.json" );
        assertNotNull(file);
        assertTrue(file.contains("Fury Sliver"));

        String file2 = fileService.loadFile( "src/test/resources/badFile.json" );
        assertNull(file2);
    }

    @Test
    void createFolder() throws IOException {
        String file = "src/test/resources/tempFiles";
        FileUtils.deleteDirectory( new File( file ));
        assertFalse(fileService.doesFileExist( file ));

        assertTrue(fileService.createFolder( file ));

        assertTrue(fileService.doesFileExist( file ));

        //cleanup
        FileUtils.deleteDirectory( new File( file ));
        assertFalse(fileService.doesFileExist( file ));
    }


    @Test
    void saveFile() throws IOException {
        String file = "src/test/resources/tempFiles";
        FileUtils.deleteDirectory( new File( file ));
        assertFalse(fileService.doesFileExist( file ));

        file+="test.txt";
        fileService.saveFile(file,"THIS IS A TEST FILE");

        assertTrue(fileService.doesFileExist( file ));


        String loadedFile = fileService.loadFile( file );
        assertEquals("THIS IS A TEST FILE",loadedFile);

        //cleanup
        file = "src/test/resources/tempFiles";
        FileUtils.deleteDirectory( new File( file ));
        assertFalse(fileService.doesFileExist( file ));
    }

    @Test
    void getAvailableBulkDataFiles() throws IOException {
        String file = "src/test/resources/tempFiles/";
        FileUtils.deleteDirectory( new File( file+"/bulkData" ));
        assertFalse(fileService.doesFileExist( file+"/bulkData" ));

        assertTrue(fileService.createFolder( file+"/bulkData" ));

        fileService.saveFile(file+"bulkData/2022-10-14T09-05-29.json","THIS IS A TEST FILE");
        fileService.saveFile(file+"bulkData/2022-07-31T21-06-45.json","THIS IS A TEST FILE");
        fileService.saveFile(file+"bulkData/2022-07-26T09-04-49.json","THIS IS A TEST FILE");
        fileService.saveFile(file+"bulkData/2022-07.tar.gz","THIS IS A TEST FILE");


        List<String> files = fileService.getAvailableBulkDataFiles();
        assertEquals(3,files.size());

        FileUtils.deleteDirectory( new File( file+"/bulkData" ));
    }
}
