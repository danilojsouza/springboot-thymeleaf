package danilojs.springboot.security;

import org.springframework.security.authentication.BadCredentialsException;
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
	public UserDetails loadUserByUsername(String s) {
		Usuario usuario = this.repositorioUsuario.findByUsuario(s);
		if (usuario != null) {
			if (usuario.getAtivo() == 1) {
				UsuarioPrincipal usuarioPrincipal = new UsuarioPrincipal(usuario);
				return usuarioPrincipal;
			} else {
				throw new BadCredentialsException("Este usuário está desativado.");
			}
		}
		throw new UsernameNotFoundException("Campos Usuário e/ou Senha inválidos!");
	}
}