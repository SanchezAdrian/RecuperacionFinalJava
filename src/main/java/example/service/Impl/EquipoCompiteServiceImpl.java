package example.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.EquipoCompiteConverter;
import example.entity.EquipoCompite;
import example.model.EquipoCompiteModel;
import example.repository.EquipoCompiteJpaRepository;
import example.service.EquipoCompiteService;

@Service
public class EquipoCompiteServiceImpl implements EquipoCompiteService{

	@Autowired
	@Qualifier("equipoCompiteJpaRepository")
	private EquipoCompiteJpaRepository equipoCompiteJpaRepository;
	
	@Autowired
	@Qualifier("equipoCompiteConverter")
	private EquipoCompiteConverter equipoCompiteConverter;

	@Override
	public List<EquipoCompiteModel> getListequipoCompite() {
		List<EquipoCompiteModel> equipoCompiteModel=new ArrayList<EquipoCompiteModel>();
		for(EquipoCompite equipoCompiteEntity: equipoCompiteJpaRepository.findAll())
			equipoCompiteModel.add(equipoCompiteConverter.entity2model(equipoCompiteEntity));
		return equipoCompiteModel;
	}

	@Override
	public EquipoCompite addEquipoCompite(EquipoCompiteModel equipoCompiteModel) {
		EquipoCompite equipoCompite=equipoCompiteConverter.model2entity(equipoCompiteModel);
		return equipoCompiteJpaRepository.save(equipoCompite);
	}
	
	@Override
	public EquipoCompite findOne(int id) {
		EquipoCompite equipoCompite = equipoCompiteJpaRepository.findById(id).get();
		return equipoCompite;
	}

	@Override
	public int removeEquipoCompite(int id) {
		equipoCompiteJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public EquipoCompite updateEquipoCompite(EquipoCompiteModel equipoCompiteModel, int id) {
		EquipoCompite equipoCompite=equipoCompiteConverter.model2entity(equipoCompiteModel);
		equipoCompite.setId(id);
		return equipoCompiteJpaRepository.save(equipoCompite);
	}
}
