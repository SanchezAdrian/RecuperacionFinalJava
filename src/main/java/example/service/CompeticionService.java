package example.service;

import java.util.List;

import example.entity.Competicion;
import example.model.CompeticionModel;


public interface CompeticionService {

	public abstract List<CompeticionModel> getListcompeticion();
	public abstract Competicion addCompeticion(CompeticionModel competicionModel);
	public abstract int removeCompeticion(int id);
	
	public abstract Competicion findOne(int id);
	Competicion updateCompeticion(CompeticionModel competicionModel, int id);
}
