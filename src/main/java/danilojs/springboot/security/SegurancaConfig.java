package danilojs.springboot.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@SuppressWarnings("deprecation")
@Configuration
public class SegurancaConfig extends WebSecurityConfigurerAdapter{
	private UsuarioPrincipalDetailsService usuarioPrincipalDetailsService;
	
	public SegurancaConfig(UsuarioPrincipalDetailsService usuarioPrincipalDetailsService) {
        this.usuarioPrincipalDetailsService = usuarioPrincipalDetailsService;
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(autenticarProvedor());
    }
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
		.antMatchers("/", "/alunos*", "/instituicoes*").permitAll()
		.antMatchers("/instituicoes/**").hasRole("ADMIN")
		.antMatchers("/alunos/**").hasAnyRole("GERENTE, ADMIN")
		.anyRequest().permitAll()
		.and()
        .exceptionHandling()
        .accessDeniedPage("/negado")
		.and()
		.formLogin()
		.loginPage("/login")
		.usernameParameter("usuario")
        .passwordParameter("senha")
        .permitAll()
        .and()
        .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout")
		;
  }
   
	DaoAuthenticationProvider autenticarProvedor() {
		DaoAuthenticationProvider dap = new DaoAuthenticationProvider();
		dap.setPasswordEncoder(passwordEncoder());
		dap.setUserDetailsService(this.usuarioPrincipalDetailsService);
		return dap;
	}
	
  @Bean
  public PasswordEncoder passwordEncoder() {
      return NoOpPasswordEncoder.getInstance();
  }
}