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
@Table(name="tipo")
public class Tipo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_tipo")
	private Integer id_tipo;
	
	@Column(name="nombre")
	private String nombre;
	
	
	@OneToMany(mappedBy="id_mov",cascade=CascadeType.REMOVE)
	private List <Movimiento> TipoMovimiento;
	

	public List<Movimiento> getTipoMovimiento() {
		return TipoMovimiento;
	}

	public void setTipoMovimiento(List<Movimiento> tipoMovimiento) {
		TipoMovimiento = tipoMovimiento;
	}

	public Tipo(Integer id, String nombre) {
		super();
		this.id_tipo = id;
		this.nombre = nombre;
	}
	
	public Tipo() {
		
	}

	public Integer getId() {
		return id_tipo;
	}

	public void setId(Integer id) {
		this.id_tipo = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
}
