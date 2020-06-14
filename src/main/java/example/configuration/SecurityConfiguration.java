package example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import example.service.Impl.UsuarioServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("usuarioServiceImpl")
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(usuarioServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("comomolaspring.herokuapp.com/*","/css/*", "/imagenes/*","/pokemons/registro","/pokemons/addUsuario").permitAll().anyRequest().authenticated().and()
		.formLogin().loginPage("/pokemons/login").loginProcessingUrl("/logincheck").usernameParameter("name")
		.passwordParameter("password").defaultSuccessUrl("/pokemons/home").permitAll().and().logout()
		.logoutUrl("/logout").logoutSuccessUrl("/pokemons/login").permitAll();
	}
}
