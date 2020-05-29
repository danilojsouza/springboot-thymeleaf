package danilojs.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import danilojs.springboot.entities.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    Usuario findByUsuario(String usuario);
}