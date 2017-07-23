package in.expedite.wiki.service;

import java.util.Date;
import java.util.List;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.wiki.entity.Page;
import in.expedite.wiki.repository.PageRepository;

@Service
public class PageService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
   @Autowired
   private PageRepository pageRepo;
	
   public String getParsedText(String content){
		Parser parser = Parser.builder().build();
		Node document = parser.parse(content);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String html=renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
		return html;
	}
   
   public Page savePage(Page page,String username){
	   
	   page.setUrlFriendlyTitle(page.getTitle().replaceAll(" ", "_").toLowerCase());
	   page.setCreatedBy(username);
	   page.setModifiedBy(username);
	   page.setCreatedDate(new Date());
	   page.setModifiedDate(new Date());
	   return pageRepo.save(page);
   }
   
   public Page getPage(Long id){
	   return pageRepo.findOne(id);
   }
   
   public Page getPage(String title){
	   return pageRepo.findByUrlFriendlyTitle(title.replaceAll(" ", "_").toLowerCase());
   }

   public List<Page> searchPage(String title){
	   return pageRepo.findByTitleLikeIgnoreCase(title);
   }
}
