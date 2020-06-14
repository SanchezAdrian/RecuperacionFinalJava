package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.UsuarioRol;

@Repository("usuarioRolJpaRepository")
public interface UsuarioRolJpaRepository extends JpaRepository <UsuarioRol, Serializable> {

	

}
