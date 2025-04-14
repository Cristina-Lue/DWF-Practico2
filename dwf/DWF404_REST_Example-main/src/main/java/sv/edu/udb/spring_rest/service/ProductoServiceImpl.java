package sv.edu.udb.spring_rest.service;

import org.springframework.stereotype.Service;
import sv.edu.udb.spring_rest.model.Producto;
import sv.edu.udb.spring_rest.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        Optional<Producto> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            Producto actualizado = productoExistente.get();
            actualizado.setNombre(producto.getNombre());
            actualizado.setDescripcion(producto.getDescripcion());
            actualizado.setPrecio(producto.getPrecio());
            actualizado.setCategoria(producto.getCategoria());
            actualizado.setStock(producto.getStock());
            actualizado.setDisponible(producto.getDisponible());
            return productoRepository.save(actualizado);
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
