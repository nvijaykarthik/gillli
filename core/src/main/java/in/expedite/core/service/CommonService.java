package in.expedite.core.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommonService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Value("${expedite.file.repo.path}")
	private String repoHome;
	

	public String getParsedText(String content){
		
		Parser parser = Parser.builder().build();
		Node document = parser.parse(content);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String html=renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
		
		return html;
	}
	
	public void saveFileToRepo(MultipartFile file,String subDirectory){
			
		File repoHomeDir=new File(getRepoHome()+File.separator+subDirectory);
		
		if(!repoHomeDir.exists()){
			repoHomeDir.mkdirs();
		}
		
		String name=file.getOriginalFilename();

        try(BufferedOutputStream stream=new BufferedOutputStream(new FileOutputStream(new File(repoHomeDir+File.separator+name)));) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (Exception e) {
        	log.error("Error while writting the file ",e);
        }
	}
	
	
	public String getRepoHome(){
		String currentUsersHomeDir = System.getProperty("user.home");

		if(StringUtils.isEmpty(repoHome)){
			repoHome=currentUsersHomeDir+"repository";
		}
		if(StringUtils.isEmpty(repoHome)){
			repoHome="repository";
		}
		return repoHome;
	}

}
