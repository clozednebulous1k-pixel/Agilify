package br.com.projetoMarajoara.Config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException {

    	if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADM"))) {
            response.sendRedirect("/adm/eventos");
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_FUNCIONARIO"))) {        	
        	response.sendRedirect("/adm/eventos");
        	
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_MORADOR"))) {
            response.sendRedirect("/morador/eventos");
        } else {
        	
            response.sendRedirect("/"); 
        }
    }
    
}
