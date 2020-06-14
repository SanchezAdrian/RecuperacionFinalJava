package example.service;

import java.util.List;

import example.entity.EquipoCompite;
import example.model.EquipoCompiteModel;

public interface EquipoCompiteService {
	
	public abstract List<EquipoCompiteModel> getListequipoCompite();
	public abstract EquipoCompite addEquipoCompite(EquipoCompiteModel equipoCompiteModel);
	public abstract int removeEquipoCompite(int id);
	
	public abstract EquipoCompite findOne(int id);
	EquipoCompite updateEquipoCompite(EquipoCompiteModel equipoCompiteModel, int id);

}
