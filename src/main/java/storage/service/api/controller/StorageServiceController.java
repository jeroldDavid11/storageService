package storage.service.api.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import storage.service.api.model.DirectoryModel;
import storage.service.api.model.FileModel;
import storage.service.api.model.StorageServiceResp;
import storage.service.api.service.DirectoryService;
import storage.service.api.service.FileService;

@RestController
@RequestMapping("/api/")
public class StorageServiceController {
	
	  @Autowired
	  private DirectoryService directoryService;
	  @Autowired
	  private FileService fileService;
	 
	  @RequestMapping(value="/createdirectory", method=RequestMethod.POST)
	  public StorageServiceResp controllerDirectory(@RequestBody DirectoryModel directoryModel) throws IOException {
	    return directoryService.createDirectory(directoryModel);
	  }
	  
	  @RequestMapping(value="/addFile", method=RequestMethod.POST)
	  public StorageServiceResp addFile(
		      @RequestParam(name = "file", required = false) MultipartFile file,@RequestParam("filePath") String filePath) throws IOException {
		  
		 FileModel fileModel = new FileModel(); 
		 fileModel.setFilename(file.getOriginalFilename());
		 fileModel.setMultipartFile(file);
		 fileModel.setFileSize(file.getSize());
		 fileModel.setFileType(file.getContentType());
		 fileModel.setFilePath(filePath);
	 
		 return fileService.addFile(fileModel);
	  }
	  
	  @RequestMapping(value="/calculateSize", method=RequestMethod.POST)
	  public StorageServiceResp calculateSize(@RequestBody DirectoryModel directoryModel) throws IOException {
	    return directoryService.calculateSize(directoryModel);
	  }
}  
	  

