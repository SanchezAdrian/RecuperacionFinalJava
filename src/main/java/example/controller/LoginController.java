package example.controller;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.constantes.constantes;


@Controller
@RequestMapping("/pokemons")
public class LoginController {

	private static final Log LOG=LogFactory.getLog(LoginController.class);
	
	
	@GetMapping("/login")
	public String showLogin(Model model, @RequestParam(name="error", required=false) String error,  @RequestParam(name="logout", required=false) String logout) {
		LOG.info("METHOD: showLoginForm -- PARAMS: error= "+error+", logout= "+logout);
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);
	   LOG.info("Volviendo a la vista de login");
	   return constantes.LOGIN_VIEW;
	}
	
	
	
	@GetMapping("/loginsuccess")
	public String loginCheck() {
		LOG.info("METHOD: loginCheck()");
		return "redirect:/pokemon/"+constantes.HOME;
	}

}
