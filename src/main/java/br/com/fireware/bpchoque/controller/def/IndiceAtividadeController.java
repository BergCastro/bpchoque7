package br.com.fireware.bpchoque.controller.def;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/indiceAtividades")
public class IndiceAtividadeController {

	
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("IndiceAtividade");
		
		
		return mv;
		
		
	 
	}
	
	@RequestMapping("/500")
	public String erroServidor() {
		return "500";
	}
	
}
