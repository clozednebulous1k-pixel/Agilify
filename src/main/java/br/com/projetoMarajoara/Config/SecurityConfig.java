package br.com.projetoMarajoara.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import br.com.projetoMarajoara.Service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private MyUserDetailsService us;
	
	@Autowired
	private CustomLoginSuccessHandler successHandler;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
				
		return http
			    .csrf(customizer -> customizer.disable())
			    .authorizeHttpRequests(request -> request
			    		.requestMatchers(
			    				"/",
			    				"/login",
			    				"/addMorador",
			    				"/updateSenha",
			    				"/emailSender/sendCodMor",
			    				"/Style/**",
			    				"/Scripts/**",
			    				"/Images/**",
			    				"/h2-console/**"
			    		).permitAll()
			    		.requestMatchers("/adm/**").hasAnyRole("ADM", "FUNCIONARIO")
			    		.requestMatchers("/morador/**").hasRole("MORADOR")
			    		.anyRequest().authenticated()
			    )
			    .formLogin(form -> form
			    		.loginPage("/") 
			    		.loginProcessingUrl("/login")
			    		.successHandler(successHandler)
			    		.permitAll()
			    )
			    .logout(logout -> logout
			    		.logoutUrl("/logout")
			    	    .logoutSuccessUrl("/")
			    	    .invalidateHttpSession(true)
			    	    .deleteCookies("JSESSIONID")
			    	    .permitAll()
			    )
			    .headers(headers -> headers.frameOptions(frame -> frame.disable()))
			    .build();
	}	

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(us);
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
		
}
