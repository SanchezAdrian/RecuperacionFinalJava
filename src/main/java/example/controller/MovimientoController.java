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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import example.constantes.constantes;
import example.converter.MovimientoConverter;
import example.converter.TipoConverter;
import example.entity.Movimiento;
import example.model.MovimientoModel;
import example.model.PokemonMovimientoModel;
import example.model.TipoModel;
import example.service.PokemonMovimientoService;
import example.service.Impl.MovimientoServiceImpl;
import example.service.Impl.TipoServiceImpl;

@Controller 
@RequestMapping("/pokemons")
public class MovimientoController {
	
	private int oldId;

	@Autowired
	@Qualifier("movimientoServiceImpl")
	MovimientoServiceImpl movimientoService;
	
	@Autowired
	@Qualifier("movimientoConverter")
	MovimientoConverter movimientoConverter;
	
	@Autowired
	@Qualifier("tipoServiceImpl")
	TipoServiceImpl tipoService;
	
	@Autowired
	@Qualifier("tipoConverter")
	TipoConverter tipoConverter;
	
	@Autowired
	@Qualifier("pokemonMovimientoServiceImpl")
	private PokemonMovimientoService pokemonMovimientoService;
	
	@GetMapping("/listaMovimientos")
	public ModelAndView listaMovimientos() {
		ModelAndView mav=new ModelAndView(constantes.MOVIMIENTO_VIEW);
		mav.addObject("movimiento",new Movimiento());
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createMovimiento")
	public ModelAndView createEntrenador() {
		ModelAndView mav = new ModelAndView(constantes.CREATEMOVIMIENTO_VIEW);
		mav.addObject("movimiento",new Movimiento());
		mav.addObject("tipos",tipoService.getListtipo());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addMovimiento")
	public ModelAndView addCompeticion(@ModelAttribute("movimiento") MovimientoModel movimientoModel,@RequestParam(name="tip") int tip) {
		ModelAndView mav = new ModelAndView(constantes.MOVIMIENTO_VIEW);
		
		for(TipoModel tipoModel:tipoService.getListtipo()) {
			if(tip == tipoModel.getId()) {
				movimientoModel.setTipoMovimiento(tipoModel);
			}
		}
		movimientoService.addMovimiento(movimientoModel);
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/deleteMovimiento/{id}")
	public String deleteCompeticion(@PathVariable("id") int id) {
		
		for(PokemonMovimientoModel pokeMov:pokemonMovimientoService.getListPokemonMovimiento()) {
			if(pokeMov.getMov1().getId() == id) {
				pokemonMovimientoService.removePokemonMovimiento(pokeMov.getId());
			}
			if(pokeMov.getMov2().getId() == id) {
				pokemonMovimientoService.removePokemonMovimiento(pokeMov.getId());
			}
			if(pokeMov.getMov3().getId() == id) {
				pokemonMovimientoService.removePokemonMovimiento(pokeMov.getId());
			}
			if(pokeMov.getMov4().getId() == id) {
				pokemonMovimientoService.removePokemonMovimiento(pokeMov.getId());
			}
		}
		
		movimientoService.removeMovimiento(id);
		return "redirect:/pokemons/listaMovimientos";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editMovimiento/{id}")
	public ModelAndView editEntrenador(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(constantes.EDITMOVIMIENTO_VIEW);
		mav.addObject("movimiento",new Movimiento());
		mav.addObject("tipos",tipoService.getListtipo());
		this.oldId=id;
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateMovimiento")
	public ModelAndView updateCompeticion(@ModelAttribute("movimiento") MovimientoModel movimientoModel,@RequestParam(name="tip") int tip) {
		ModelAndView mav = new ModelAndView(constantes.MOVIMIENTO_VIEW);
		
		for(TipoModel tipoModel:tipoService.getListtipo()) {
			if(tip == tipoModel.getId()) {
				movimientoModel.setTipoMovimiento(tipoModel);
			}
		}
		movimientoService.updateMovimiento(movimientoModel,this.oldId);
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		return mav;
	}
}
