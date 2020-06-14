package example.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import example.constantes.constantes;
import example.converter.MovimientoConverter;
import example.converter.PokemonConverter;
import example.converter.PokemonMovimientoConverter;
import example.entity.EquipoCompite;
import example.entity.PokemonMovimiento;
import example.model.MovimientoModel;
import example.model.PokemonModel;
import example.model.PokemonMovimientoModel;
import example.service.PokemonMovimientoService;
import example.service.PokemonService;
import example.service.Impl.MovimientoServiceImpl;

@Controller
@RequestMapping("/pokemons")
public class PokemonMovimientoController {
	
	private int oldId;
	
	@Autowired
	@Qualifier("pokemonServiceImpl")
	private PokemonService pokemonService;
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	@Autowired
	@Qualifier("movimientoServiceImpl")
	MovimientoServiceImpl movimientoService;
	
	@Autowired
	@Qualifier("movimientoConverter")
	MovimientoConverter movimientoConverter;
	
	@Autowired
	@Qualifier("pokemonMovimientoServiceImpl")
	private PokemonMovimientoService pokemonMovimientoService;
	
	@Autowired
	@Qualifier("pokemonMovimientoConverter")
	private PokemonMovimientoConverter pokemonMovimientoConverter;
	
	@GetMapping("/detailPokemon/{id}")
	public ModelAndView detailPokemon(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView(constantes.POKEMOV_VIEW);
		 List <PokemonMovimientoModel> movsets = new ArrayList<PokemonMovimientoModel>();
		 
		 
		for(PokemonMovimientoModel mov:pokemonMovimientoService.getListPokemonMovimiento()) {
			if(id == mov.getPoke().getId()) {
				movsets.add(mov);
			}
		}
		mav.addObject("pokemonMovimientos",movsets);
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		mav.addObject("pokemons",pokemonService.getListpokemon());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/AsignarMoveSet")
	public ModelAndView AsignarMoveSet() {
		ModelAndView mav = new ModelAndView(constantes.CREATEPOKEMOV_VIEW);
		mav.addObject("pokemonMovimiento",new PokemonMovimiento());
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		mav.addObject("pokemons",pokemonService.getListpokemon());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addMovSet")
	public String addMovSet(@RequestParam(name="mov1") int mov1,
			@RequestParam(name="mov2") int mov2,@RequestParam(name="mov3") int mov3,@RequestParam(name="mov4") int mov4,@RequestParam(name="id") int id) {
		
		PokemonMovimientoModel pokemonMovimientoModel = new PokemonMovimientoModel();
		
		
		for(MovimientoModel movimientoModel:movimientoService.getListmovimiento()) {
			
			if(mov1==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov1(movimientoModel);
			}
			if(mov2==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov2(movimientoModel);
			}
			if(mov3==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov3(movimientoModel);
			}
			if(mov4==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov4(movimientoModel);
			}
		}
		
		for(PokemonModel pokemonModel:pokemonService.getListpokemon()) {
			System.out.println(id);
			System.out.println(pokemonModel);
			if(id==pokemonModel.getId()) {
				pokemonMovimientoModel.setPoke(pokemonModel);
			}
		}
		
		
		System.out.println(pokemonMovimientoModel.toString());
		PokemonMovimiento pok=pokemonMovimientoConverter.model2entity(pokemonMovimientoModel);
		System.out.println(pok.toString());
		pokemonMovimientoService.addPokemonMovimiento(pokemonMovimientoModel);
		
		
		
		return "redirect:/pokemons/listaPokemons";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="deleteMovSet/{id}")
	public String deleteEquipoMovSet(@PathVariable("id") int id, RedirectAttributes flash) {
	    pokemonMovimientoService.removePokemonMovimiento(id);
	    return "redirect:/pokemons/listaPokemons";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editPokemonMovimiento/{id}")
	public ModelAndView editCompeticonEquipo(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView(constantes.EDITPOKEMOV_VIEW);
		this.oldId=id;
		mav.addObject("pokemonMovimiento",new PokemonMovimiento());
		mav.addObject("movimientos",movimientoService.getListmovimiento());
		mav.addObject("pokemons",pokemonService.getListpokemon());
		return mav;
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateMovSet")
	public String updateMovSet(@RequestParam(name="mov1") int mov1,
			@RequestParam(name="mov2") int mov2,@RequestParam(name="mov3") int mov3,@RequestParam(name="mov4") int mov4,@RequestParam(name="id") int id) {
		
		PokemonMovimientoModel pokemonMovimientoModel = new PokemonMovimientoModel();
		
		
		for(MovimientoModel movimientoModel:movimientoService.getListmovimiento()) {
			
			if(mov1==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov1(movimientoModel);
			}
			if(mov2==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov2(movimientoModel);
			}
			if(mov3==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov3(movimientoModel);
			}
			if(mov4==movimientoModel.getId()) {
				pokemonMovimientoModel.setMov4(movimientoModel);
			}
		}
		
		for(PokemonModel pokemonModel:pokemonService.getListpokemon()) {
			System.out.println(id);
			System.out.println(pokemonModel);
			if(id==pokemonModel.getId()) {
				pokemonMovimientoModel.setPoke(pokemonModel);
			}
		}
	pokemonMovimientoService.updatePokemonMovimiento(pokemonMovimientoModel, oldId);
	return "redirect:/pokemons/listaPokemons";
}
	
}
