package storage.service.api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class FileModelTest {
	@Test
	void test() {
		FileModel fileModel = new FileModel();
		fileModel.setFilename("Filename");
		fileModel.setFilePath("FilePath");
		fileModel.setFileType(".test");
		fileModel.setId("0");
		fileModel.setFileSize(1L);
		MultipartFile multipartFile = null;
		fileModel.setMultipartFile(multipartFile);
		
		
		assertNotNull(fileModel.getId());
		assertNotNull(fileModel.getFilename());
		assertNotNull(fileModel.getFilePath());
		assertNotNull(fileModel.getFileType());
		assertNotNull(fileModel.getFileSize());
		assertNull(fileModel.getMultipartFile());
	}

}
