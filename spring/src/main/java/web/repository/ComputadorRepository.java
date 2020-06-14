package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.entities.Computador;

public interface ComputadorRepository extends JpaRepository<Computador, Integer> {

}
