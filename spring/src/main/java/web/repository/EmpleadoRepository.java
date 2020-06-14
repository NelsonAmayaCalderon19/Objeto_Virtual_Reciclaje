package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import web.entities.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
	

}
