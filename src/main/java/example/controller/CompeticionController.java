package example.controller;

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
import org.springframework.web.servlet.ModelAndView;

import example.constantes.constantes;
import example.converter.CompeticionConverter;
import example.entity.Competicion;
import example.model.CompeticionModel;
import example.model.EquipoCompiteModel;
import example.service.CompeticionService;
import example.service.EquipoCompiteService;

@Controller
@RequestMapping("/pokemons")
public class CompeticionController {
	
	private static final Log LOG=LogFactory.getLog(CompeticionController.class);
	
	private int idUpdate;
	
	@Autowired
	@Qualifier("competicionServiceImpl")
	private CompeticionService competicionService;
	
	@Autowired
	@Qualifier("competicionConverter")
	private CompeticionConverter competicionConverter;
	
	@Autowired
	@Qualifier("equipoCompiteServiceImpl")
	private EquipoCompiteService equipoCompiteService;
	
	@GetMapping("/listaCompeticiones")
	public ModelAndView listaCompeticons() {
		ModelAndView mav=new ModelAndView(constantes.COMPETICION_VIEW);
		mav.addObject("competicion",new Competicion());
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
	

	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/createCompeticion")
	public ModelAndView createCompeticion() {
		ModelAndView mav = new ModelAndView(constantes.CREATECOMPETICION_VIEW);
		mav.addObject("competicion",new Competicion());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/addCompeticion")
	public ModelAndView addCompeticion(@ModelAttribute("competicion") CompeticionModel competicionModel) {
		LOG.info(competicionModel);
		ModelAndView mav = new ModelAndView(constantes.COMPETICION_VIEW);
		competicionService.addCompeticion(competicionModel);
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value="/deleteCompeticion/{id}")
	public String deleteCompeticion(@PathVariable("id") int id,@ModelAttribute("competicion") CompeticionModel competicionModel) {
		
		for(EquipoCompiteModel eq:equipoCompiteService.getListequipoCompite()) {
			if(competicionModel.getId() == eq.getId_competicion().getId()) {
				equipoCompiteService.removeEquipoCompite(eq.getId());
			}
		}
		
		competicionService.removeCompeticion(id);
		return "redirect:/pokemons/listaCompeticiones";
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/editCompeticion/{id}")
	public ModelAndView editCompeticion(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView(constantes.EDITCOMPETICION_VIEW);
		this.idUpdate=id;
		mav.addObject("competicion",new Competicion());
		return mav;
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/updateCompeticion")
	public ModelAndView updateCiclista(@ModelAttribute("competicion") CompeticionModel competicionModel) {
		ModelAndView mav=new ModelAndView(constantes.COMPETICION_VIEW);
		competicionService.updateCompeticion(competicionModel, this.idUpdate);
		mav.addObject("competicions",competicionService.getListcompeticion());
		return mav;
	}
}
