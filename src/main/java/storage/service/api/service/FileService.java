package storage.service.api.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;
import storage.service.api.model.FileModel;
import storage.service.api.model.StorageServiceResp;

@Service
public class FileService {
	
	public StorageServiceResp addFile(FileModel fileModel) throws IOException {
		  StorageServiceResp response = new StorageServiceResp();
	      String fileName = fileModel.getFilename();
	    try {
	      if (fileName.contains("..")) {
	    	  response.setMessage("Sorry! Filename contains invalid path sequence ");
			  response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
			   return response;
	      }
	     
		  Path path = Paths.get(fileModel.getFilePath());
	      Path targetLocation = path.resolve(fileName);
	      Files.copy(fileModel.getMultipartFile().getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
	      response.setMessage("File Transfer Complete");
		  response.setStatus(HttpURLConnection.HTTP_OK);
		  response.setFilename(fileName);
		  response.setSize(fileModel.getMultipartFile().getSize());
		  LocalDateTime time = LocalDateTime.now();
		  response.setCreateDate(time.now().toString());
		  
	    } catch (IOException ex) {
	      response.setMessage("Could not store file"  + ex.getMessage().toString());
		  response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
	    }
        return response;
	}
	
}
