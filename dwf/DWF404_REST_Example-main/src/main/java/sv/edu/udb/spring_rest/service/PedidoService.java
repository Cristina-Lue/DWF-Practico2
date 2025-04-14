package sv.edu.udb.spring_rest.service;

import sv.edu.udb.spring_rest.model.Pedido;

import java.util.List;

public interface PedidoService {
    List<Pedido> obtenerPedidos();
    Pedido crearPedido(Pedido pedido) throws Exception;
    Pedido obtenerPorId(Long id);

    Pedido actualizarPedido(Long id, Pedido pedido);
}
