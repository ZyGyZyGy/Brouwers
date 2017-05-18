package be.vdab.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ADMINISTRATOR = "administrator";
    private static final String USER = "user";
    
    private final DataSource dataSource;
    
    public SecurityConfig(DataSource dataSource) {
	this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("");
	// TODO authentication via database
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception {
	web.ignoring()
	.antMatchers("/images/**")
	.antMatchers("/styles/**")
	.antMatchers("/scripts/**");
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
	http.formLogin().loginPage("/login")
				.and().logout().logoutSuccessUrl("/")
				.and().authorizeRequests()
				.antMatchers("/brouwers", "/brouwers/beginnaam", "/brouwers/opAlfabet").hasAnyAuthority(ADMINISTRATOR, USER)
				.antMatchers(HttpMethod.POST, "/brouwers").hasAuthority(ADMINISTRATOR)
				.antMatchers("/", "/login").permitAll()
				.antMatchers("/**").authenticated()
				.and().exceptionHandling().accessDeniedPage("/WEB-INF/JSP/forbidden.jsp");
    }
    
}









