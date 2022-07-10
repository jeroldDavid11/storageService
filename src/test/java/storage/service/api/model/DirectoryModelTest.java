package storage.service.api.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

class DirectoryModelTest {
	@Test
	void test() {
		DirectoryModel directoryModel = new DirectoryModel();
		directoryModel.setDirectoryName("DirectoryName");
		directoryModel.setDirectoryPath("DirectoryPath");
		directoryModel.setFdirectorySize("1231233");
		FileModel fileModel = new FileModel();
		fileModel.setFilename("Filename");
		fileModel.setFilePath("FilePath");
		fileModel.setFileSize(1L);
		MultipartFile multipartFile =null;
		fileModel.setMultipartFile(multipartFile);
		fileModel.setId("id");
		
	    ArrayList<FileModel> listFileMulti = new ArrayList<FileModel>();
		fileModel.setMultipartFile(multipartFile);
		directoryModel.setFileModel(listFileMulti);
		directoryModel.setId("id");
		
		assertNotNull(directoryModel.getId());
		assertNotNull(directoryModel.getDirectoryName());
		assertNotNull(directoryModel.getDirectoryPath());
		assertNotNull(directoryModel.getFdirectorySize());
		
	}

}
