package sv.edu.udb.spring_rest.service;

import sv.edu.udb.spring_rest.model.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> obtenerTodos();
    Producto guardar(Producto producto);
    Producto obtenerPorId(Long id);
    Producto actualizar(Long id, Producto producto);
    void eliminar(Long id);
}
