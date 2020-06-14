package example.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import example.converter.UsuarioConverter;
import example.entity.Usuario;
import example.entity.UsuarioRol;
import example.model.UsuarioModel;
import example.repository.UsuarioJpaRepository;

@Service
public class UsuarioServiceImpl implements UserDetailsService {
	
	@Autowired
	@Qualifier("usuarioJpaRepository")
	private UsuarioJpaRepository usuarioJpaRepository;
	
	@Autowired
	@Qualifier("usuarioConverter")
	private UsuarioConverter usuarioConverter;
	
	private List<GrantedAuthority> buildAuthorities(Set<UsuarioRol> UsuarioRols){
		Set<GrantedAuthority> auths=new HashSet<GrantedAuthority>();
		
		for(UsuarioRol UsuarioRol : UsuarioRols) {
			auths.add(new SimpleGrantedAuthority(UsuarioRol.getRole()));
		}
		return new ArrayList<GrantedAuthority>(auths);
	}

	private User buildUser(Usuario usuario, List<GrantedAuthority> authorities) {
		return new User(usuario.getUsername(),usuario.getPassword(),usuario.isEnabled(),true,true,true,authorities);
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		Usuario usuario=usuarioJpaRepository.findByUsername(name);
		List<GrantedAuthority> authorities =buildAuthorities(usuario.getUsuarioRol());
		return buildUser(usuario,authorities);
	}
	
	
	public Usuario addUsuario(UsuarioModel usuarioModel) {
		Usuario usuario = usuarioConverter.model2entity(usuarioModel);
		usuario.setEnabled(true);
		return usuarioJpaRepository.save(usuario);
	}
	
	public List<Usuario> getListuser() {
		List<Usuario> user=new ArrayList<Usuario>();
		for(Usuario usuario: usuarioJpaRepository.findAll())
			user.add((usuario));
		return user;
	}

	public int removeUser(Usuario user) {
	
		usuarioJpaRepository.delete(user);
		return 0;
	}
}
