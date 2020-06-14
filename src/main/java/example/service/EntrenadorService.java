package example.service;

import java.util.List;

import example.entity.Entrenador;
import example.model.EntrenadorModel;

public interface EntrenadorService {
	
	public abstract List<EntrenadorModel> getListentrenador();
	public abstract Entrenador addEntrenador(EntrenadorModel entrenadorModel);
	public abstract int removeEntrenador(int id);
	
	public abstract Entrenador findOne(int id);
	Entrenador updateEntrenador(EntrenadorModel entrenadorModel, int id);

}
