package de.wwweasel.WhiteCollar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RedirectController {
	
	@RequestMapping("*")
	public String dedirect() {
		return "redirect:/stores/";
	}
}
