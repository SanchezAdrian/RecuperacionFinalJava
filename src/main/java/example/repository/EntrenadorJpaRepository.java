package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Entrenador;

@Repository("entrenadorJpaRepository")
public interface EntrenadorJpaRepository extends JpaRepository<Entrenador, Serializable> {

}
