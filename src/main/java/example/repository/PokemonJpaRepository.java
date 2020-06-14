package example.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.entity.Pokemon;

@Repository("pokemonJpaRepository")
public interface  PokemonJpaRepository extends JpaRepository< Pokemon, Serializable> {

}
