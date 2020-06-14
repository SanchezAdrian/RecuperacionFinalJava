package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import example.entity.Usuario;

@Repository("usuarioJpaRepository")
public interface  UsuarioJpaRepository extends JpaRepository< Usuario, Serializable> {
	public abstract  Usuario findByUsername(String username);

}
