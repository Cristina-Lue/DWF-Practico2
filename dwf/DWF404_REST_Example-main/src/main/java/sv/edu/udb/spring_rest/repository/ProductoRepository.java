package sv.edu.udb.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.spring_rest.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long>{
}
