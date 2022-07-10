package storage.service.api.model;

import java.util.ArrayList;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
public class DirectoryModel {
	private String id;
	private String directoryName;
	private String directoryPath;
	private String fdirectorySize;
	private ArrayList<FileModel> FileModel = new ArrayList<FileModel>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public String getDirectoryPath() {
		return directoryPath;
	}
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	public String getFdirectorySize() {
		return fdirectorySize;
	}
	public void setFdirectorySize(String fdirectorySize) {
		this.fdirectorySize = fdirectorySize;
	}
	public ArrayList<FileModel> getFileModel() {
		return FileModel;
	}
	public void setFileModel(ArrayList<FileModel> fileModel) {
		FileModel = fileModel;
	}

}
