package in.expedite.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUploadResponse {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private String fileName;
	private Long fileSize;
	private String projectFileType;
	
	public FileUploadResponse(String fileName, Long fileSize, String projectFileType) {
		super();
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.projectFileType = projectFileType;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getProjectFileType() {
		return projectFileType;
	}
	public void setProjectFileType(String projectFileType) {
		this.projectFileType = projectFileType;
	}
	@Override
	public String toString() {
		return "FileUploadResponse [fileName=" + fileName + ", fileSize=" + fileSize + ", projectFileType="
				+ projectFileType + "]";
	}
	
}
