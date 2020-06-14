package example.controller;



import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.component.PDF;
import example.constantes.constantes;
import example.converter.EntrenadorConverter;
import example.converter.EquipoConverter;
import example.converter.PokemonConverter;
import example.entity.Equipo;
import example.model.CompeticionModel;
import example.model.EntrenadorModel;
import example.model.EquipoCompiteModel;
import example.model.EquipoModel;
import example.model.PokemonModel;
import example.service.EntrenadorService;
import example.service.EquipoCompiteService;
import example.service.EquipoService;
import example.service.PokemonService;

@Controller
@RequestMapping("/pokemons")
public class EquipoController {
	
	private static final Log LOG=LogFactory.getLog(EquipoController.class);
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
	@Qualifier("pokemonServiceImpl")
	private PokemonService pokemonService;
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	@Autowired
	@Qualifier("equipoCompiteServiceImpl")
	private EquipoCompiteService equipoCompiteService;
	
	private PDF pdfpokemon = new PDF();
	
	
	@GetMapping("/listaEquipos")
	public ModelAndView listaEquipos() {
		ModelAndView mav=new ModelAndView(constantes.EQUIPO_VIEW);
		mav.addObject("equipo",new Equipo());
		mav.addObject("equipos",equipoService.getListequipo());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@GetMapping("/createEquipo")
	public ModelAndView createEquipo() {
		ModelAndView mav=new ModelAndView(constantes.CREATEEQUIPO_VIEW);
		mav.addObject("equipo",new Equipo());
		mav.addObject("entrenadors",entrenadorService.getListentrenador());
		LOG.info("devolvemos junto a la vistas las entidades que vamos a utilizar");
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@PostMapping("/addEquipo")
	public ModelAndView addEquipo(@ModelAttribute("equipo") EquipoModel equipoModel,@RequestParam(name="en") int en) {
		ModelAndView mav = new ModelAndView(constantes.EQUIPO_VIEW);
		
		for(EntrenadorModel entrenadorModel:entrenadorService.getListentrenador()) {
			if(en == entrenadorModel.getId()) {
				equipoModel.setEntrenador(entrenadorModel);
			}
		}
		equipoService.addEquipo(equipoModel);
		mav.addObject("equipos",equipoService.getListequipo());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@RequestMapping(value="/deleteEquipo/{id}")
	public String deleteEquipo(@PathVariable("id") int id, RedirectAttributes flash,@ModelAttribute("equipo") EquipoModel equipoModel) {
		
		for(EquipoCompiteModel eq:equipoCompiteService.getListequipoCompite()) {
			if(id == eq.getId_equipo().getId()) {
				equipoCompiteService.removeEquipoCompite(eq.getId());
			}
		}
	    equipoService.removeEquipo(id);
	    return "redirect:/pokemons/listaEquipos";
	}
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@RequestMapping(value="/detailEquipo/{id}")
	public ModelAndView detailEquipo(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(constantes.DETAILEQUIPO_VIEW);
		
		for(EquipoModel equipoModel:equipoService.getListequipo()) {
			if(id == equipoModel.getId()) {
				mav.addObject("equipo",equipoConverter.model2entity(equipoModel));
			}
			for(PokemonModel pokemonModel:pokemonService.getListpokemon()) {
				if(pokemonModel.getEquiposPokemon() == equipoConverter.model2entity(equipoModel)) {
					mav.addObject(pokemonModel);
				}
		}
		
	}
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@GetMapping("/editEquipo/{id}")
	public ModelAndView editEquipo(@PathVariable("id") int id) {
		ModelAndView mav=new ModelAndView(constantes.EDITEQUIPO_VIEW);
		mav.addObject("equipo",new Equipo());
		mav.addObject("entrenadors",entrenadorService.getListentrenador());
		this.oldId = id;
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ENTRENADOR')")
	@PostMapping("/updateEquipo")
	public ModelAndView updateEquipo(@ModelAttribute("equipo") EquipoModel equipoModel,@RequestParam(name="en") int en) {
		ModelAndView mav = new ModelAndView(constantes.EQUIPO_VIEW);
		
		for(EntrenadorModel entrenadorModel:entrenadorService.getListentrenador()) {
			if(en == entrenadorModel.getId()) {
				equipoModel.setEntrenador(entrenadorModel);
			}
		}
		equipoService.updateEquipo(equipoModel, oldId);
		mav.addObject("equipos",equipoService.getListequipo());
		return mav;
	}
	
	
	
	
	@GetMapping("/generarPDF")
    public String generarPDF(RedirectAttributes flash) {
        List <EquipoModel> list=equipoService.getListequipo();
        pdfpokemon.PDFEquipo(list);
        flash.addFlashAttribute("success","PDF generado correctamente");
        return "redirect:/pokemons/home";
        
    }
	
	

	
}
