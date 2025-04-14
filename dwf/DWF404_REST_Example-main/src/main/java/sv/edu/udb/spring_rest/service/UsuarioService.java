package sv.edu.udb.spring_rest.service;

import sv.edu.udb.spring_rest.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> obtenerTodos();
    Usuario guardar(Usuario usuario) throws Exception;


}