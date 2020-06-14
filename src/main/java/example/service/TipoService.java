package example.service;

import java.util.List;

import example.entity.Tipo;
import example.model.TipoModel;

public interface TipoService {

	
	public abstract List<TipoModel> getListtipo();
	public abstract Tipo addTipo(TipoModel tipoModel);
	public abstract int removeTipo(int id);
	public abstract Tipo findOne(int id);
	Tipo updateTipo(TipoModel tipoModel, int id);
}
