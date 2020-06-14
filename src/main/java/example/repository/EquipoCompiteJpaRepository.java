
package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity. EquipoCompite;

@Repository("equipoCompiteJpaRepository")
public interface  EquipoCompiteJpaRepository extends JpaRepository< EquipoCompite, Serializable> {

}
