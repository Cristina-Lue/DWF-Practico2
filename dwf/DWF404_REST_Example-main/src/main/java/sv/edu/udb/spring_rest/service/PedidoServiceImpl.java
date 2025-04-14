package sv.edu.udb.spring_rest.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.spring_rest.model.Pedido;
import sv.edu.udb.spring_rest.model.PedidoProducto;
import sv.edu.udb.spring_rest.model.Producto;
import sv.edu.udb.spring_rest.repository.PedidoProductoRepository;
import sv.edu.udb.spring_rest.repository.PedidoRepository;
import sv.edu.udb.spring_rest.repository.ProductoRepository;

import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final ProductoRepository productoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             PedidoProductoRepository pedidoProductoRepository,
                             ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoProductoRepository = pedidoProductoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Pedido> obtenerPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido obtenerPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido) {
        return null;
    }

    @Override
    @Transactional
    public Pedido crearPedido(Pedido pedido) throws Exception {
        double total = 0.0;

        // Procesar productos del pedido
        for (PedidoProducto pp : pedido.getProductos()) {
            Producto producto = productoRepository.findById(pp.getProducto().getId())
                    .orElseThrow(() -> new Exception("Producto no encontrado: ID " + pp.getProducto().getId()));

            // Verificar disponibilidad y stock del producto
            if (!producto.getDisponible() || producto.getStock() < pp.getCantidad()) {
                throw new Exception("Producto fuera de stock o no disponible: " + producto.getNombre());
            }

            // Calcular subtotal para cada producto
            double subtotal = producto.getPrecio() * pp.getCantidad();
            pp.setPrecioUnitario(producto.getPrecio());
            pp.setSubtotal(subtotal);
            total += subtotal;

            // Actualizar el stock del producto
            producto.setStock(producto.getStock() - pp.getCantidad());
            productoRepository.save(producto);
        }

        // Validar que el total sea mayor a 0
        if (total <= 0) {
            throw new Exception("El total del pedido debe ser mayor que 0.");
        }

        // Establecer el total en el pedido
        pedido.setTotal(total);
        Pedido pedidoGuardado = pedidoRepository.save(pedido);

        // Guardar los productos del pedido en la tabla intermedia PedidoProducto
        for (PedidoProducto pp : pedido.getProductos()) {
            pp.setPedido(pedidoGuardado);
            pedidoProductoRepository.save(pp);
        }

        return pedidoGuardado;
    }
}
