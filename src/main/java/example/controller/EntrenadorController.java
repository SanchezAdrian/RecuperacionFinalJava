package example.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.constantes;
import example.converter.EntrenadorConverter;
import example.converter.EquipoConverter;
import example.entity.Entrenador;
import example.entity.Usuario;
import example.model.EntrenadorModel;
import example.model.EquipoModel;
import example.model.UsuarioModel;
import example.service.EntrenadorService;
import example.service.EquipoService;
import example.service.Impl.UsuarioServiceImpl;

@Controller
@RequestMapping("/pokemons")
public class EntrenadorController {
	
	private int oldId;
	
	@Autowired
	@Qualifier("equipoServiceImpl")
	private EquipoService equipoService;
	
	@Autowired
	@Qualifier("equipoConverter")
	private EquipoConverter equipoConverter;
	
	@Autowired
	@Qualifier("entrenadorServiceImpl")
	private EntrenadorService entrenadorService;
	
	@Autowired
	@Qualifier("entrenadorConverter")
	private EntrenadorConverter entrenadorConverter;
	
	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioService;
	
	private static final Log LOG=LogFactory.getLog(EntrenadorController.class);
	
	@GetMapping("/listaEntrenadores")
	public ModelAndView listaEntrenadores() {
		ModelAndView mav=new ModelAndView(constantes.ENTRENADOR_VIEW);
		mav.addObject("entrenador",new Entrenador());
		mav.addObject("entrenadors",entrenadorService.getListentrenador());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createEntrenador")
	public ModelAndView createEntrenador() {
		ModelAndView mav = new ModelAndView(constantes.CREATEENTRENADOR_VIEW);
		mav.addObject("entrenador",new Entrenador());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addEntrenador")
	public ModelAndView addEntrenador(RedirectAttributes flash,@RequestParam("file") MultipartFile foto, @ModelAttribute("entrenador") EntrenadorModel entrenadorModel) {
		ModelAndView mav = new ModelAndView(constantes.ENTRENADOR_VIEW);
		
		if (!foto.isEmpty()) {
			Path directorioRecursos=Paths.get("src/main//resources//static/image");
			String rootPath=directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+entrenadorModel.getNombre()+".jpg");
				Files.write(rutaCompleta, bytes);
				flash.addAttribute("info","Se ha subido correctamente");
				entrenadorModel.setFoto("/image/"+entrenadorModel.getNombre()+".jpg");	
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			entrenadorModel.setFoto("/image/avatar.jpg");	
		}
		
		
		entrenadorService.addEntrenador(entrenadorModel);
		mav.addObject("entrenadors",entrenadorService.getListentrenador());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/deleteEntrenador/{id}")
	public String deleteEntrenador(@PathVariable("id") int id,RedirectAttributes flash,@ModelAttribute("entrenador") EntrenadorModel entrenador) {
		
		
		for(EquipoModel equipoModel:equipoService.getListequipo()) {
			if(id == equipoModel.getEntrenador().getId()) {
				LOG.info((equipoModel.getId()));
				equipoService.removeEquipo(equipoModel.getId());
			}
		}
		
		
		entrenadorService.removeEntrenador(id);
		return "redirect:/pokemons/listaEntrenadores";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editEntrenador/{id}")
	public ModelAndView editEntrenador(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(constantes.EDITENTRENADOR_VIEW);
		mav.addObject("entrenador",new Entrenador());
		this.oldId = id;
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateEntrenador")
	public ModelAndView updateEntrenador(RedirectAttributes flash,@RequestParam("file") MultipartFile foto, @ModelAttribute("entrenador") EntrenadorModel entrenadorModel) {
		ModelAndView mav = new ModelAndView(constantes.ENTRENADOR_VIEW);
		
		if (!foto.isEmpty()) {
			Path directorioRecursos=Paths.get("src/main//resources//static/image");
			String rootPath=directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+entrenadorModel.getNombre()+".jpg");
				Files.write(rutaCompleta, bytes);
				flash.addAttribute("info","Se ha subido correctamente");
				entrenadorModel.setFoto("/image/"+entrenadorModel.getNombre()+".jpg");	
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			entrenadorModel.setFoto("/image/avatar.jpg");	
		}
		
		
		entrenadorService.updateEntrenador(entrenadorModel, oldId);
		mav.addObject("entrenadors",entrenadorService.getListentrenador());
		return mav;
	}
	

}
