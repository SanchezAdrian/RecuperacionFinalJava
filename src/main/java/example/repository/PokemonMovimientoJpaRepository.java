package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.PokemonMovimiento;

@Repository("pokemonMovimientoJpaRepository")
public interface PokemonMovimientoJpaRepository extends JpaRepository< PokemonMovimiento, Serializable> {

}
