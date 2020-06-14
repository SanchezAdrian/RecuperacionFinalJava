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
import example.converter.EquipoConverter;
import example.converter.MovimientoConverter;
import example.converter.PokemonConverter;
import example.converter.TipoConverter;
import example.entity.Pokemon;
import example.model.PokemonModel;
import example.model.PokemonMovimientoModel;
import example.model.TipoModel;
import example.service.EquipoService;
import example.service.PokemonMovimientoService;
import example.service.PokemonService;
import example.service.Impl.MovimientoServiceImpl;
import example.service.Impl.TipoServiceImpl;

@Controller
@RequestMapping("/pokemons")
public class PokemonController {
	
	private static final Log LOG=LogFactory.getLog(PokemonController.class);
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
	

	@Autowired
	@Qualifier("equipoServiceImpl")
	private EquipoService equipoService;
	
	@Autowired
	@Qualifier("equipoConverter")
	private EquipoConverter equipoConverter;
	
	@Autowired
	@Qualifier("pokemonServiceImpl")
	private PokemonService pokemonService;
	
	@Autowired
	@Qualifier("pokemonConverter")
	private PokemonConverter pokemonConverter;
	
	@GetMapping("/listaPokemons")
	public ModelAndView listaPokemons() {
		ModelAndView mav=new ModelAndView(constantes.POKEMON_VIEW);
		mav.addObject("pokemon",new Pokemon());
		mav.addObject("pokemons",pokemonService.getListpokemon());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createPokemon")
	public ModelAndView createPokemon() {
		ModelAndView mav = new ModelAndView(constantes.CREATEPOKEMON_VIEW);
		mav.addObject("pokemon",new Pokemon());
		mav.addObject("tipos",tipoService.getListtipo());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addPokemon")
	public ModelAndView addPokemon(RedirectAttributes flash,@RequestParam("file") MultipartFile foto, @ModelAttribute("pokemon") PokemonModel pokemonModel,@RequestParam(name="tip") int tip) {
		ModelAndView mav=new ModelAndView(constantes.POKEMON_VIEW);
		
		if (!foto.isEmpty()) {
			Path directorioRecursos=Paths.get("src/main//resources//static/image");
			String rootPath=directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+pokemonModel.getNombre()+"poke.jpg");
				Files.write(rutaCompleta, bytes);
				flash.addAttribute("info","Se ha subido correctamente");
				pokemonModel.setFoto("/image/"+pokemonModel.getNombre()+"poke.jpg");	
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			pokemonModel.setFoto("/image/avatar.jpg");	
		}
		for(TipoModel tipoModel:tipoService.getListtipo()) {
			if(tip == tipoModel.getId()) {
				pokemonModel.setTipoPokemon(tipoModel);
			}
		}
		pokemonService.addPokemon(pokemonModel);
		mav.addObject("pokemons",pokemonService.getListpokemon());	
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/deletePokemon/{id}")
	public String deletePokemon(@PathVariable("id") int id,RedirectAttributes flash,@ModelAttribute("pokemon") PokemonModel pokemonModel) {
		
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
		
		pokemonService.removePokemon(id);
		return "redirect:/pokemons/listaPokemons";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editPokemon/{id}")
	public ModelAndView editPokemon(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(constantes.EDITPOKEMON_VIEW);
		mav.addObject("pokemon",new Pokemon());
		mav.addObject("tipos",tipoService.getListtipo());
		this.oldId=id;
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updatePokemon")
	public ModelAndView updatePokemon(RedirectAttributes flash,@RequestParam("file") MultipartFile foto, @ModelAttribute("pokemon") PokemonModel pokemonModel,@RequestParam(name="tip") int tip) {
		ModelAndView mav=new ModelAndView(constantes.POKEMON_VIEW);
		
		if (!foto.isEmpty()) {
			Path directorioRecursos=Paths.get("src/main//resources//static/image");
			String rootPath=directorioRecursos.toFile().getAbsolutePath();
			try {
				byte[] bytes=foto.getBytes();
				Path rutaCompleta=Paths.get(rootPath+"//"+pokemonModel.getNombre()+"poke.jpg");
				Files.write(rutaCompleta, bytes);
				flash.addAttribute("info","Se ha subido correctamente");
				pokemonModel.setFoto("/image/"+pokemonModel.getNombre()+"poke.jpg");	
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			pokemonModel.setFoto("/image/avatar.jpg");	
		}
		for(TipoModel tipoModel:tipoService.getListtipo()) {
			if(tip == tipoModel.getId()) {
				pokemonModel.setTipoPokemon(tipoModel);
			}
		}
		pokemonService.updatePokemon(pokemonModel,this.oldId);
		mav.addObject("pokemons",pokemonService.getListpokemon());	
		return mav;
	}
}
