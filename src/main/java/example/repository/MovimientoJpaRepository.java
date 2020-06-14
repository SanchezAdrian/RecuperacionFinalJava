

package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Movimiento;

@Repository("movimientoJpaRepository")
public interface  MovimientoJpaRepository extends JpaRepository< Movimiento, Serializable> {

}
