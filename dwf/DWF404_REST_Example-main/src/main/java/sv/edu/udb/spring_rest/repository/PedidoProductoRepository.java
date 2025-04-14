package sv.edu.udb.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.spring_rest.model.PedidoProducto;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto,Long> {
}
