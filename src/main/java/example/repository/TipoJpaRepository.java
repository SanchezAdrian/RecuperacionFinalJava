package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Tipo;

@Repository("tipoJpaRepository")
public interface  TipoJpaRepository extends JpaRepository< Tipo, Serializable> {

}
