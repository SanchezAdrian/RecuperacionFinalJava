package example.controller;

import java.util.ArrayList;
import java.util.List;

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

import example.constantes.constantes;
import example.converter.CompeticionConverter;
import example.converter.EquipoCompiteConverter;
import example.converter.EquipoConverter;
import example.entity.Competicion;
import example.entity.EquipoCompite;
import example.model.CompeticionModel;
import example.model.EquipoCompiteModel;
import example.model.EquipoModel;
import example.service.CompeticionService;
import example.service.EquipoCompiteService;
import example.service.EquipoService;

@Controller
@RequestMapping("/pokemons")
public class EquipoCompiteController {
	
	private int oldId;
	
	@Autowired
	@Qualifier("equipoCompiteServiceImpl")
	private EquipoCompiteService equipoCompiteService;
	
	@Autowired
	@Qualifier("equipoCompiteConverter")
	private EquipoCompiteConverter equipoCompiteConverter;
	
	@Autowired
	@Qualifier("equipoServiceImpl")
	private EquipoService equipoService;
	
	@Autowired
	@Qualifier("equipoConverter")
	private EquipoConverter equipoConverter;
	
	@Autowired
	@Qualifier("competicionServiceImpl")
	private CompeticionService competicionService;
	
	@Autowired
	@Qualifier("competicionConverter")
	private CompeticionConverter competicionConverter;
	

	@GetMapping("/verCompeticionEquipos/{id}")
	public ModelAndView listaEntrenadores(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView(constantes.EQUIPOCOMPITE_VIEW);
		 List <EquipoCompiteModel> competidores = new ArrayList<EquipoCompiteModel>();
		for(EquipoCompiteModel comp:equipoCompiteService.getListequipoCompite()) {
			if(id == comp.getId_competicion().getId()) {
				competidores.add(comp);
				
			}
		}
		mav.addObject("equipoCompites",competidores);
		mav.addObject("equipos",equipoService.getListequipo());
		mav.addObject("competicion",competicionService.getListcompeticion());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ENTRENADOR')")
	@GetMapping("/AsignarEquipo")
	public ModelAndView AsignarEquipo() {
		ModelAndView mav = new ModelAndView(constantes.CREATEEQUIPOCOMPITE_VIEW);
		mav.addObject("equipoCompite",new EquipoCompite());
		mav.addObject("equipos",equipoService.getListequipo());
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ENTRENADOR')")
	@PostMapping("/addEquipoCompite")
	public ModelAndView addEquipoCompite(@ModelAttribute("equipoCompite") EquipoCompiteModel equipoCompiteModel,@RequestParam(name="comp") int comp,@RequestParam(name="eq") int eq) {
		ModelAndView mav = new ModelAndView(constantes.COMPETICION_VIEW);
		
		for(CompeticionModel competicionModel:competicionService.getListcompeticion()) {
			if(comp==competicionModel.getId()) {
				equipoCompiteModel.setId_competicion(competicionModel);
			}
		}

		for(EquipoModel equipoModel:equipoService.getListequipo()) {
			if(eq==equipoModel.getId()) {
				equipoCompiteModel.setId_equipo(equipoModel);
			}
		}
		
		equipoCompiteService.addEquipoCompite(equipoCompiteModel);
		mav.addObject("competicion",new Competicion());
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ENTRENADOR')")
	@RequestMapping(value="deleteEquipoCompeticion/{id}")
	public String deleteEquipoCompeticion(@PathVariable("id") int id, RedirectAttributes flash) {
	    equipoCompiteService.removeEquipoCompite(id);
	    return "redirect:/pokemons/listaCompeticiones";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ENTRENADOR')")
	@GetMapping("/editCompeticionEquipo/{id}")
	public ModelAndView editCompeticonEquipo(@PathVariable("id")int id) {
		ModelAndView mav=new ModelAndView(constantes.EDITEQUIPOCOMPITE_VIEW);
		this.oldId=id;
		mav.addObject("equipoCompite",new EquipoCompite());
		mav.addObject("equipos",equipoService.getListequipo());
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
		
	}
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_ENTRENADOR')")
	@PostMapping("/updateEquipoCompite")
	public ModelAndView updateEquipoCompite(@ModelAttribute("equipoCompite") EquipoCompiteModel equipoCompiteModel,@RequestParam(name="comp") int comp,@RequestParam(name="eq") int eq) {
		ModelAndView mav = new ModelAndView(constantes.COMPETICION_VIEW);
		
		for(CompeticionModel competicionModel:competicionService.getListcompeticion()) {
			if(comp==competicionModel.getId()) {
				equipoCompiteModel.setId_competicion(competicionModel);
			}
		}

		for(EquipoModel equipoModel:equipoService.getListequipo()) {
			if(eq==equipoModel.getId()) {
				equipoCompiteModel.setId_equipo(equipoModel);
			}
		}
		equipoCompiteService.updateEquipoCompite(equipoCompiteModel, oldId);
		mav.addObject("competicion",new Competicion());
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
	
}
