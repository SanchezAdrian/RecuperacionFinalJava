package example.service;

import java.util.List;

import example.entity.Movimiento;
import example.model.MovimientoModel;

public interface MovimientoService {

	public abstract List<MovimientoModel> getListmovimiento();
	public abstract Movimiento addMovimiento(MovimientoModel movimientoModel);
	public abstract int removeMovimiento(int id);
	public abstract void findById  (int id);
	public abstract Movimiento findOne(int id);
	Movimiento updateMovimiento(MovimientoModel movimientoModel, int id);
}
