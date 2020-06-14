package example.service.Impl;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import example.converter.MovimientoConverter;
import example.entity.Movimiento;
import example.model.MovimientoModel;
import example.repository.MovimientoJpaRepository;
import example.service.MovimientoService;

@Service
public class MovimientoServiceImpl implements MovimientoService{
	
	@Autowired
	@Qualifier("movimientoJpaRepository")
	private MovimientoJpaRepository movimientoJpaRepository;
	
	@Autowired
	@Qualifier("movimientoConverter")
	private MovimientoConverter movimientoConverter;

	@Override
	public List<MovimientoModel> getListmovimiento() {
		List<MovimientoModel> movimientoModel=new ArrayList<MovimientoModel>();
		for(Movimiento movimientoEntity: movimientoJpaRepository.findAll())
			movimientoModel.add(movimientoConverter.entity2model(movimientoEntity));
		return movimientoModel;
	}

	@Override
	public Movimiento addMovimiento(MovimientoModel movimientoModel) {
		Movimiento movimiento=movimientoConverter.model2entity(movimientoModel);
		return movimientoJpaRepository.save(movimiento);
	}
	
	@Override
	public Movimiento findOne(int id) {
		Movimiento movimiento = movimientoJpaRepository.findById(id).get();
		return movimiento;
	}
	

	@Override
	public int removeMovimiento(int id) {
		movimientoJpaRepository.deleteById(id);
		return 0;
	}

	@Override
	public Movimiento updateMovimiento(MovimientoModel MovimientoModel, int id) {
		Movimiento movimiento=movimientoConverter.model2entity(MovimientoModel);
		movimiento.setId(id);
		return movimientoJpaRepository.save(movimiento);
	}

	@Override
	public void findById(int id) {
		// TODO Auto-generated method stub
		
	}

}
