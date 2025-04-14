package sv.edu.udb.spring_rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.spring_rest.model.Usuario;
import sv.edu.udb.spring_rest.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Obtener todos los usuarios
    @Override
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    // Guardar un nuevo usuario
    @Override
    public Usuario guardar(Usuario usuario) throws Exception {
        // Validar que el email tenga el formato correcto
        if (!usuario.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            throw new Exception("El email proporcionado no es válido.");
        }

        // Verificar si el email ya existe en la base de datos
        Optional<Usuario> existingUser = usuarioRepository.findByEmail(usuario.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("El email ya está registrado.");
        }

        // Si pasa todas las validaciones, guardar el usuario
        return usuarioRepository.save(usuario);
    }
}
