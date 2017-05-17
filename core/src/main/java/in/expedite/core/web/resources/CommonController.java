package in.expedite.core.web.resources;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.expedite.core.service.CommonService;
import in.expedite.core.utils.ExJsonResponse;
import in.expedite.core.utils.FileUploadResponse;

@RestController
@RequestMapping("/resource/common")
public class CommonController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(path="/parseToHtml",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public String getParsedContent(@RequestBody String content) {
		return commonService.getParsedText(content);
	}
	
	
	@RequestMapping(value="/uploadDoc", method=RequestMethod.POST)
    public FileUploadResponse handleFileUpload(@RequestParam(name="file",required=false) MultipartFile file,@RequestParam("projectId") String projectId){
		
        if (!file.isEmpty()) {
        	commonService.saveFileToRepo(file, "document"+File.separator+projectId);
        }else{
        	throw new RuntimeException("IOError Saving file.");
        }
        return new FileUploadResponse(file.getOriginalFilename(),file.getSize(),"document");
    }
	
	@RequestMapping(value = "/downloadDoc", method = RequestMethod.GET)
	public void getDocumentFile(@RequestParam("projectId") String projectId, @RequestParam("fileName") String fileName,  HttpServletResponse response) {
	    try {
	      
	       String repoHome=commonService.getRepoHome();
	       repoHome=repoHome+File.separator+"document"+File.separator+projectId;
	       InputStream is = new FileInputStream(new File(repoHome+File.separator+fileName));
	       response.addHeader("Content-disposition", "attachment;filename="+fileName);
	       //response.setContentType("application/octet-stream");
	       
	       IOUtils.copy(is, response.getOutputStream());
	       
	       response.flushBuffer();
	       
	    } catch (IOException ex) {
	    	LOG.error("Error writing file to output stream. Filename was '{}'", fileName, ex);
	    	throw new RuntimeException("IOError writing file to output stream");
	    }

	}
}
