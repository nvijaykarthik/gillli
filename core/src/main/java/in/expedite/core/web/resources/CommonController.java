package in.expedite.core.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.service.CommonService;

@RestController
@RequestMapping("/resource/common")
public class CommonController {
	
	@Autowired
	private CommonService commonService;

	@RequestMapping(path="/parseToHtml",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String getParsedContent(@RequestBody String content) {
		return commonService.getParsedText(content);
	}
}
