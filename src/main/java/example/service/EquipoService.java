package example.service;

import java.util.List;

import example.entity.Equipo;
import example.model.EquipoModel;


public interface EquipoService {

	public abstract List<EquipoModel> getListequipo();
	public abstract Equipo addEquipo(EquipoModel equipoModel);
	public abstract int removeEquipo(int id);
	
	public abstract Equipo findOne(int id);
	Equipo updateEquipo(EquipoModel equipoModel, int id);
}
