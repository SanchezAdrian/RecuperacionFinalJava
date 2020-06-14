package example.model;


import org.springframework.lang.NonNull;

public class EquipoCompiteModel {
	
	@NonNull
	private Integer id;
	 
	@NonNull
	private EquipoModel id_equipo;
	
	@NonNull
	private CompeticionModel id_competicion;
	
	
	private Integer puesto;
	
	
	private Integer puntos;


	public EquipoCompiteModel(Integer id, EquipoModel id_equipo, CompeticionModel id_competicion, Integer puesto, Integer puntos) {
		super();
		this.id = id;
		this.id_equipo = id_equipo;
		this.id_competicion = id_competicion;
		this.puesto = puesto;
		this.puntos = puntos;
	}

	public EquipoCompiteModel() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EquipoModel getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(EquipoModel id_equipo) {
		this.id_equipo = id_equipo;
	}

	public CompeticionModel getId_competicion() {
		return id_competicion;
	}

	public void setId_competicion(CompeticionModel id_competicion) {
		this.id_competicion = id_competicion;
	}

	public Integer getPuesto() {
		return puesto;
	}

	public void setPuesto(Integer puesto) {
		this.puesto = puesto;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}
	
	
}
