package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.entities.Inventario;
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

}
