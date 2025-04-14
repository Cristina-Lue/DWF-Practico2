package sv.edu.udb.spring_rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.udb.spring_rest.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
