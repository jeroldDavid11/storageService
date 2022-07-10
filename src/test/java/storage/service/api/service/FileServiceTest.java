package storage.service.api.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import storage.service.api.model.DirectoryModel;
import storage.service.api.model.FileModel;

@RunWith(SpringRunner.class)
@SpringBootTest
class FileServiceTest {
	
	@Autowired
    private FileService fileService;
	
	@Autowired
    private DirectoryService directoryService;

	@Test
	void testIOException() throws IOException {
		
		DirectoryModel directory = new DirectoryModel();
		directory.setDirectoryPath("/Test10");
        directoryService.createDirectory(directory);
        
		FileModel fileModel = new FileModel();
		fileModel.setFilename("test");
		fileModel.setFilePath("/Test10");
		MockMultipartFile kmlfile = new MockMultipartFile("data", "filename.txt", "text/plain", "some kml".getBytes());
		fileModel.setMultipartFile(kmlfile);
		
		
		assertEquals(fileService.addFile(fileModel).getStatus(),400);
	}
	

	@Test
	void testSuccess() throws IOException {
		
		DirectoryModel directory = new DirectoryModel();
		directory.setDirectoryPath("/Test10");
        directoryService.createDirectory(directory);
        
		FileModel fileModel = new FileModel();
		fileModel.setFilename("filename.txt");
		fileModel.setFilePath("/ServiceTest/Test10");
		MockMultipartFile kmlfile = new MockMultipartFile("data", "filename.txt", "text/plain", "some kml".getBytes());
		fileModel.setMultipartFile(kmlfile);
		
		
		assertEquals(fileService.addFile(fileModel).getStatus(),200);
	}


}
