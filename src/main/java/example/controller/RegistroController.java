package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.constantes;
import example.converter.TestCrypt;
import example.entity.Usuario;
import example.model.EntrenadorModel;
import example.model.UsuarioModel;
import example.model.UsuarioRolModel;
import example.service.Impl.UsuarioRolServiceImpl;
import example.service.Impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/pokemons")
public class RegistroController {

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	@Qualifier("usuarioRolServiceImpl")
	private UsuarioRolServiceImpl usuarioRolServiceImpl;
	
	private TestCrypt enc=new TestCrypt();
	
	@GetMapping("/registro")
	public ModelAndView registrar() {
		ModelAndView mav =new ModelAndView(constantes.REG);
		mav.addObject("usuario",new Usuario());
		return mav;
	}
	
	@PostMapping("/addUsuario")
    public ModelAndView addUser(@RequestParam(value = "numero", required = false) String numero,@RequestParam(value = "email", required = false) String email,@ModelAttribute("usuario") UsuarioModel usuarioModel,@RequestParam(name="rol")  String rol,EntrenadorModel entrenadorModel, UsuarioRolModel usuarioRolModel, Model model, RedirectAttributes flash) {
        ModelAndView mav = new ModelAndView();
        usuarioModel.setPassword(enc.encrypt(usuarioModel.getPassword()));
        if(rol.equals("ROLE_ENTRENADOR")) {
        	entrenadorModel.setEmail(email);
        	entrenadorModel.setTelefono(numero);
        	entrenadorModel.setNombre(usuarioModel.getUsername());
        	entrenadorModel.setFoto("1");
        	usuarioModel.setEntrenador(entrenadorModel);
        	
        }
        usuarioServiceImpl.addUsuario(usuarioModel);
        usuarioRolModel.setRole(rol);
        usuarioRolModel.setUsuario(usuarioModel);
     
        usuarioRolServiceImpl.addUsuario(usuarioRolModel);
        mav.setViewName("redirect:/pokemons/home");
        return mav;
    }
	
	/* @PostMapping("/addUsuario")
	public ModelAndView addUser(@ModelAttribute("usuario") UsuarioModel usuarioModel, Model model, RedirectAttributes flash) {
		ModelAndView mav = new ModelAndView(constantes.LOGIN_VIEW);
		usuarioModel.setPassword(enc.encrypt(usuarioModel.getPassword()));
		usuarioServiceImpl.addUsuario(usuarioModel);
		return mav;
	}
	*/
}
