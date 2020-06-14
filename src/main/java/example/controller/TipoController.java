package example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.constantes;
import example.converter.PokemonConverter;
import example.converter.TipoConverter;
import example.entity.Tipo;
import example.model.MovimientoModel;
import example.model.PokemonModel;
import example.model.TipoModel;
import example.service.PokemonService;
import example.service.TipoService;
import example.service.Impl.MovimientoServiceImpl;

@Controller
@RequestMapping("/pokemons")
public class TipoController {
	private int oldId;
	
	@Autowired
	@Qualifier("tipoServiceImpl")
	private TipoService tipoService;
	
	@Autowired
	@Qualifier("tipoConverter")
	private TipoConverter tipoConverter;
	
	@Autowired
	@Qualifier("movimientoServiceImpl")
	MovimientoServiceImpl movimientoService;
	
	@Autowired
	@Qualifier("pokemonServiceImpl")
	private PokemonService pokemonService;
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	@GetMapping("/listaTipos")
	public ModelAndView listaTipos() {
		ModelAndView mav=new ModelAndView(constantes.TIPO_VIEW);
		mav.addObject("tipo",new Tipo());
		mav.addObject("tipos",tipoService.getListtipo());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createTipo")
	public ModelAndView createTipo() {
		ModelAndView mav=new ModelAndView(constantes.CREATETIPO_VIEW);
		mav.addObject("tipo",new Tipo());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addTipo")
	public ModelAndView addTipo(@ModelAttribute("tipo") TipoModel tipoModel) {
		ModelAndView mav = new ModelAndView(constantes.TIPO_VIEW);
		tipoService.addTipo(tipoModel);
		mav.addObject("tipos",tipoService.getListtipo());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/deleteTipo/{id}")
	public String deleteEquipo(@PathVariable("id") int id, RedirectAttributes flash,@ModelAttribute("tipo") TipoModel tipoModel) {
		
		for(MovimientoModel movimientoModel:movimientoService.getListmovimiento()) {
			if(tipoModel.getId() == movimientoModel.getTipoMovimiento().getId()) {
				movimientoService.removeMovimiento(movimientoModel.getId());
			}
		}
		
		for(PokemonModel pokemonModel:pokemonService.getListpokemon()) {
			if(tipoModel.getId() == pokemonModel.getTipoPokemon().getId()) {
				pokemonService.removePokemon(pokemonModel.getId());
			}
		}
		 tipoService.removeTipo(id);
		    
		    return "redirect:/pokemons/listaTipos";
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editTipo/{id}")
	public ModelAndView editTipo(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView(constantes.EDITTIPO_VIEW);
		mav.addObject("tipo",new Tipo());
		this.oldId = id;
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateTipo")
	public ModelAndView updateTipo(@ModelAttribute("tipo") TipoModel tipoModel) {
		ModelAndView mav = new ModelAndView(constantes.TIPO_VIEW);
		tipoService.updateTipo(tipoModel,this.oldId);
		mav.addObject("tipos",tipoService.getListtipo());
		return mav;
	}
		
	   
	}
	
	


