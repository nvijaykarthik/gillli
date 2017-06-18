package in.expedite.wiki.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.wiki.entity.Page;
import in.expedite.wiki.repository.PageRepository;

@Service
public class PageService {

   @Autowired
   private PageRepository pageRepo;
	
   public String getParsedText(String content){
		Parser parser = Parser.builder().build();
		Node document = parser.parse(content);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String html=renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
		return html;
	}
   
   public Page addPage(Page page){
	   
	   page.setUrlFriendlyTitle(page.getTitle().replaceAll(" ", "_").toLowerCase());
	   return pageRepo.save(page);
   }
   
   public Page getPage(Long id){
	   return pageRepo.findOne(id);
   }
   
   public Page getPage(String title){
	   return pageRepo.findByUrlFriendlyTitle(title.replaceAll(" ", "_").toLowerCase());
   }
}
