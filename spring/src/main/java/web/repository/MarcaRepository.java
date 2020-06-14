package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.entities.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {

}
