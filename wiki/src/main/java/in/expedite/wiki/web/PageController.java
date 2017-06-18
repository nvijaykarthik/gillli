package in.expedite.wiki.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.expedite.wiki.entity.Page;
import in.expedite.wiki.service.PageService;


@Controller
@RequestMapping("/wiki")
public class PageController {

	@Autowired
	private PageService pageService;
	
	    @RequestMapping("/{title}")
	    public String greeting(@PathVariable String title, Model model) {
	        model.addAttribute("title", title);
	        Page page=pageService.getPage(title);
	        if(null!=page){
	        	String parsedText=pageService.getParsedText(page.getContent());
	            page.setHtmlContent(parsedText);
	            model.addAttribute("page",page);
		        return "page";
	        }else{
	        	return "newPage";
	        }
	    }
}
