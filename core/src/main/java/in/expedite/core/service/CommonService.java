package in.expedite.core.service;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

	public String getParsedText(String content){
		
		Parser parser = Parser.builder().build();
		Node document = parser.parse(content);
		HtmlRenderer renderer = HtmlRenderer.builder().build();
		String html=renderer.render(document);  // "<p>This is <em>Sparta</em></p>\n"
		
		return html;
	}
}
