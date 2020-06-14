package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.TipoConverter;
import example.entity.Tipo;
import example.model.TipoModel;
import example.repository.TipoJpaRepository;
import example.service.TipoService;

@Service
public class TipoServiceImpl implements TipoService{
	
	@Autowired
	@Qualifier("tipoJpaRepository")
	private TipoJpaRepository tipoJpaRepository;
	
	@Autowired
	@Qualifier("tipoConverter")
	private TipoConverter tipoConverter;

	@Override
	public List<TipoModel> getListtipo() {
		List<TipoModel> TipoModel=new ArrayList<TipoModel>();
		for(Tipo TipoEntity: tipoJpaRepository.findAll())
			TipoModel.add(tipoConverter.entity2model(TipoEntity));
		return TipoModel;
	}

	@Override
	public Tipo addTipo(TipoModel tipoModel) {
		Tipo tipo=tipoConverter.model2entity(tipoModel);
		return tipoJpaRepository.save(tipo);
	}
	
	@Override
	public Tipo findOne(int id) {
		Tipo Tipo = tipoJpaRepository.findById(id).get();
		return Tipo;
	}

	@Override
	public int removeTipo(int id) {
		tipoJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Tipo updateTipo(TipoModel tipoModel, int id) {
		Tipo tipo=tipoConverter.model2entity(tipoModel);
		tipo.setId(id);
		return tipoJpaRepository.save(tipo);
	}


}
