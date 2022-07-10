package storage.service.api.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import storage.service.api.model.DirectoryModel;
import storage.service.api.model.StorageServiceResp;
import java.net.HttpURLConnection;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.stream.Stream;

@Service
public class DirectoryService {
	@Value("${storage.main.path}")
	private String storageServicePath;
	
	public  StorageServiceResp createDirectory(DirectoryModel directory) throws IOException {
		  StorageServiceResp response = new StorageServiceResp();
		  
		  Path path = Paths.get(mainDir()+directory.getDirectoryPath());
		  try {
			     if(maxCountDir() <= 100) {
			    	 if(isReachedMaxSubFolder(directory.getDirectoryPath())){
				    	 response.setMessage("Directory Reached the Max SubFolder");
					     response.setStatus(HttpURLConnection.HTTP_CONFLICT);
					     return response;
			    	 }
			    	 Files.createDirectories(path);
			    	 response.setMessage("Directory is created! " + "Location : " + " : " +   path.toAbsolutePath());
				     response.setStatus(HttpURLConnection.HTTP_CREATED);
					 response.setFilename(path.toString());
					 LocalDateTime time = LocalDateTime.now();
					  response.setCreateDate(time.now().toString());
			     }else {
			    	 response.setMessage("Reached max Directory");
				     response.setStatus(HttpURLConnection.HTTP_REQ_TOO_LONG);
			     }
			     
		    } catch(FileAlreadyExistsException e){
		        response.setMessage("Already Directory Created!");
			    response.setStatus(HttpURLConnection.HTTP_CONFLICT);
			} catch (IOException e) {
				response.setMessage(e.getMessage());
			    response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
			}
		return response;
		
	}
	
	public StorageServiceResp calculateSize(DirectoryModel directory) throws IOException {
		StorageServiceResp response = new StorageServiceResp();
		 long size = 0;
		 Path path = Paths.get(mainDir()+directory.getDirectoryPath());
	      try (Stream<Path> walk = Files.walk(path)) {

	          size = walk
	                  .filter(Files::isRegularFile)
	                  .mapToLong(p -> {
	                      try {
	                          return Files.size(p);
	                      } catch (IOException e) {
	                          System.out.printf("Failed to get size of %s%n%s", p, e);
	                          return 0L;
	                      }
	                  })
	                  .sum();
	          
	      	response.setMessage("Directory Size");
			response.setStatus(HttpURLConnection.HTTP_OK);
			response.setFilename(path.toString());
			response.setSize(size);

	      } catch (IOException e) {
	    	  response.setMessage(e.toString());
			  response.setStatus(HttpURLConnection.HTTP_BAD_REQUEST);
	      }

		return response;
		
	}
	
	private  String mainDir() throws IOException {
		  Path pathMain = Paths.get(storageServicePath);
		  if( !Files.exists(pathMain)) {
			  Files.createDirectory(pathMain); 
		  }
		return pathMain.toString();
	}
	
    private  int maxCountDir() throws IOException {
    	File dir = new File(mainDir());
		int numberOfSubfolders = 1;
		File listDir[] = dir.listFiles();
		for (int i = 0; i < listDir.length; i++) {
		    if (listDir[i].isDirectory()) {
		            numberOfSubfolders++;
		        }
		}
		return numberOfSubfolders;
    }
    
    private static boolean isReachedMaxSubFolder(String directory) {
  	  String[] folderCount = directory.split("/");
	   if(folderCount.length >= 6){
			return true;
	   }
		return false;  	
    }
}
