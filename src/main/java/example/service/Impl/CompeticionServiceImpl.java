package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.CompeticionConverter;
import example.entity.Competicion;
import example.model.CompeticionModel;
import example.repository.CompeticionJpaRepository;
import example.service.CompeticionService;

@Service
public class CompeticionServiceImpl implements CompeticionService {
	
	@Autowired
	@Qualifier("competicionJpaRepository")
	private CompeticionJpaRepository competicionJpaRepository;
	
	@Autowired
	@Qualifier("competicionConverter")
	private CompeticionConverter competicionConverter;
	
	@Override
	public List<CompeticionModel> getListcompeticion() {
		List<CompeticionModel> competicionModel=new ArrayList<CompeticionModel>();
		for(Competicion competicionEntity: competicionJpaRepository.findAll())
			competicionModel.add(competicionConverter.entity2model(competicionEntity));
		return competicionModel;
	}
	
	@Override
	public Competicion addCompeticion(CompeticionModel competicionModel) {
		Competicion competicion=competicionConverter.model2entity(competicionModel);
		return competicionJpaRepository.save(competicion);
	}
	
	@Override
	public Competicion findOne(int id) {
		Competicion competicion = competicionJpaRepository.findById(id).get();
		return competicion;
	}
	
	@Override
	public int removeCompeticion(int id) {
		competicionJpaRepository.deleteById(id);
		return 0;
	}
	
	@Override
	public Competicion updateCompeticion(CompeticionModel competicionModel, int id) {
		Competicion competicion=competicionConverter.model2entity(competicionModel);
		competicion.setId(id);
		return competicionJpaRepository.save(competicion);
	}

}
