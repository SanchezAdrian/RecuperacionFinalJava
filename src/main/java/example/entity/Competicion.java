package example.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="competicion")
public class Competicion {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="fecha")
	private String fecha;
	
	@Column(name="lugar")
	private String lugar;

	@ManyToOne
	@JoinColumn(name="id_equ")
	@OnDelete(action=OnDeleteAction.CASCADE)
	private Equipo  EquiposCompiten;
	
	@OneToMany(mappedBy="id_equ_comp",cascade=CascadeType.REMOVE)
	private List <EquipoCompite> CompeticionEquipo;

	public Competicion(int id, String nombre, String fecha, String lugar, Equipo equiposCompiten) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.fecha = fecha;
		this.lugar = lugar;
		EquiposCompiten = equiposCompiten;
	}
	
	public Competicion() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public Equipo getEquiposCompiten() {
		return EquiposCompiten;
	}

	public void setEquiposCompiten(Equipo equiposCompiten) {
		EquiposCompiten = equiposCompiten;
	}
	

}
