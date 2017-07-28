package in.expedite.delivery.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.expedite.delivery.dao.DeliveryDao;
import in.expedite.delivery.entity.Artifacts;
import in.expedite.delivery.entity.Delivery;
import in.expedite.delivery.repository.ArtifactsRepository;
import in.expedite.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private ArtifactsRepository artifactRepo; 
	
	@Autowired
	private DeliveryDao deliveryDao;
	
	@Value("${expedite.file.repo.path}")
	private String repoHome;
	
	public List<Delivery> getDeliveryForProject(Long projectId){
		List<Delivery> delivery= deliveryDao.getDeliveryForProject(projectId);
		return delivery;
	}

	public void saveArtifactToRepo(MultipartFile file, 
			Long deliveryId,
			Long applicationId,
			String version) {
		
		File repoHomeDir=new File(getRepoHome()+File.separator+applicationId+File.separator+version);
		
		if(!repoHomeDir.exists()){
			repoHomeDir.mkdirs();
		}
		
		String name=file.getOriginalFilename();
		
        try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(repoHomeDir+File.separator+name)));) {
        	byte[] bytes = file.getBytes();
            stream.write(bytes);
        } catch (Exception e) {
        	log.error("Error while writting the file ",e);
        	return;
        }
        
        Artifacts artifacts = new Artifacts();
        artifacts.setArtifactName(name);
        artifacts.setArtifactUrl(applicationId+File.separator+version+File.separator+name);
        artifacts.setDeliveryId(deliveryId);
        artifactRepo.save(artifacts);
        
	}
	
	public String getRepoHome(){
		String currentUsersHomeDir = System.getProperty("user.home");

		if(StringUtils.isEmpty(repoHome)){
			repoHome=currentUsersHomeDir+"repository";
		}
		if(StringUtils.isEmpty(repoHome)){
			repoHome="repository";
		}
		return repoHome+File.separator+"delivery";
	}

	public void getArtifactFromRepo(String url, HttpServletResponse response,String fileName) throws IOException {
		   String repoHome=getRepoHome();
	       String absFile=repoHome+File.separator+url;
	       InputStream is = new FileInputStream(new File(absFile));
	       response.addHeader("Content-disposition", "attachment;filename="+fileName);
	       IOUtils.copy(is, response.getOutputStream());   
	}

	public Delivery saveDelivery(Delivery delivery,String username) {
		delivery.setCreatedBy(username);
		return deliveryRepository.save(delivery);
	}
}
