package web.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import web.entities.Cliente;



public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
