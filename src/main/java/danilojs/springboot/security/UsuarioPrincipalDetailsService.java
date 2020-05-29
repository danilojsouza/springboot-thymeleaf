package danilojs.springboot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import danilojs.springboot.entities.Usuario;
import danilojs.springboot.repository.RepositorioUsuario;

@Service
public class UsuarioPrincipalDetailsService implements UserDetailsService {
    private RepositorioUsuario repositorioUsuario;

    public UsuarioPrincipalDetailsService(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = this.repositorioUsuario.findByUsuario(s);
        UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuario);
        return usuarioPrincipal;
    }
}