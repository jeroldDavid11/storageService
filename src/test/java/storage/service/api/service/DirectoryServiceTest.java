package storage.service.api.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import storage.service.api.model.DirectoryModel;

@RunWith(SpringRunner.class)
@SpringBootTest
class DirectoryServiceTest {
	
	@Autowired
    private DirectoryService directoryService;
    
	@Test
	public void testDirectortCreation() throws IOException {
		DirectoryModel directory = new DirectoryModel();
		directory.setDirectoryPath("/Test10");
        assertEquals(directoryService.createDirectory(directory).getStatus(),201);
        assertEquals(directoryService.calculateSize(directory).getStatus(),200);
	}

	@Test
	public void testDirectortCalculareSizeException() throws IOException {
		DirectoryModel directory = new DirectoryModel();
		directory.setDirectoryPath("/Test22");
        assertEquals(directoryService.calculateSize(directory).getStatus(),400);
	}
	

}
