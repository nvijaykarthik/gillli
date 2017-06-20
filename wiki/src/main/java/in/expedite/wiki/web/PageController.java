package in.expedite.wiki.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import in.expedite.wiki.entity.Page;
import in.expedite.wiki.service.PageService;


@Controller
@RequestMapping("/wiki")
public class PageController {

	@Autowired
	private PageService pageService;
	
	    @RequestMapping(path="/view/{title}",method=RequestMethod.GET)
	    public String showPage(@PathVariable String title, Model model,HttpServletRequest request) {
	    	model.addAttribute("title",title);
	    	model.addAttribute("sidebar",getSideBar());
	    	model.addAttribute("contextPath",request.getContextPath());
	        Page page=pageService.getPage(title);
	        if(null!=page){
	        	String content=page.getContent();
	        	if(!StringUtils.isEmpty(content)){
	        		String parsedText=pageService.getParsedText(content);
	        	    page.setHtmlContent(parsedText);
	        	}else{
	        		page.setHtmlContent("");
	        	}
	            model.addAttribute("page",page);
		        return "page";
	        }else{
	        	return "newPage";
	        }
	    }
	    
	    @RequestMapping(path="/addPage",method=RequestMethod.GET)
	    public String addPage(@RequestParam String title,@RequestAttribute(required=false) String username,Model model,HttpServletRequest request ){
	    	model.addAttribute("contextPath",request.getContextPath());
	    	Page page = new Page();
	    	page.setTitle(title);
	    	page=pageService.savePage(page,username);
	    	
	    	model.addAttribute("page",page);
	    	model.addAttribute("sidebar",getSideBar());
	    	return "editPage";
	    }
	    
	    @RequestMapping(path="/editPage",method=RequestMethod.POST)
	    public String editPage(@ModelAttribute Page page,@RequestAttribute(required=false) String username,Model model,HttpServletRequest request ){
	    	model.addAttribute("contextPath",request.getContextPath());
	    	page=pageService.savePage(page,username);
	    	String content=page.getContent();
        	if(!StringUtils.isEmpty(content)){
        		String parsedText=pageService.getParsedText(content);
        	    page.setHtmlContent(parsedText);
        	}else{
        		page.setHtmlContent("");
        	}
	    	model.addAttribute("page",page);
	    	model.addAttribute("sidebar",getSideBar());
	    	return "page";
	    }
	    
	    @RequestMapping(path="/edit",method=RequestMethod.GET)
	    public String edit(@RequestParam String title,Model model,HttpServletRequest request ){
	    	model.addAttribute("contextPath",request.getContextPath());
	    	Page page=pageService.getPage(title);
	    	String content=page.getContent();
        	if(!StringUtils.isEmpty(content)){
        		String parsedText=pageService.getParsedText(content);
        	    page.setHtmlContent(parsedText);
        	}else{
        		page.setHtmlContent("");
        	}
        	model.addAttribute("page",page);
        	model.addAttribute("sidebar",getSideBar());
	    	return "editPage";
	    }
	    
	    
	    @RequestMapping(path="/search",method=RequestMethod.GET)
	    public String search(@RequestParam String searchtext,Model model,HttpServletRequest request ){
	    	model.addAttribute("title",searchtext);
	    	model.addAttribute("sidebar",getSideBar());
	    	model.addAttribute("contextPath",request.getContextPath());
	        Page page=pageService.getPage(searchtext);
	        if(null!=page){
	        	String content=page.getContent();
	        	if(!StringUtils.isEmpty(content)){
	        		String parsedText=pageService.getParsedText(content);
	        	    page.setHtmlContent(parsedText);
	        	}else{
	        		page.setHtmlContent("");
	        	}
	            model.addAttribute("page",page);
		        return "page";
	        }else{
	        	return "newPage";
	        }
	    }
	    
	    
	    private String getSideBar(){
	    	Page page=pageService.getPage("sidebar");
	    	if(null!=page){
	    	String content=page.getContent();
        	if(!StringUtils.isEmpty(content)){
        		String parsedText=pageService.getParsedText(content);
        	    page.setHtmlContent(parsedText);
        	}else{
        		page.setHtmlContent("");
        	}
        	
        	  return page.getHtmlContent();
	    	}
	    	return "No Sidebar";
	    }
}
