package example.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="EquipoCompite")
public class EquipoCompite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_equ_comp")
	private Integer id_equ_comp;
	 
	@ManyToOne
	@JoinColumn(name="id_equipo")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Equipo id_equipo;
	
	@ManyToOne
	@JoinColumn(name="id_competicion")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Competicion id_competicion;
	
	@Column(name="puesto")
	private Integer puesto;
	
	@Column(name="puntos")
	private Integer puntos;

	public EquipoCompite(Integer id, Equipo id_equipo, Competicion id_competicion, Integer puesto, Integer puntos) {
		super();
		this.id_equ_comp = id;
		this.id_equipo = id_equipo;
		this.id_competicion = id_competicion;
		this.puesto = puesto;
		this.puntos = puntos;
	}
	
	public EquipoCompite() {
		
	}

	public Integer getId() {
		return id_equ_comp;
	}

	public void setId(Integer id) {
		this.id_equ_comp = id;
	}

	public Equipo getId_equipo() {
		return id_equipo;
	}

	public void setId_equipo(Equipo id_equipo) {
		this.id_equipo = id_equipo;
	}

	public Competicion getId_competicion() {
		return id_competicion;
	}

	public void setId_competicion(Competicion id_competicion) {
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
