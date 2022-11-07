package br.com.fiap.globalsolution.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.globalsolution.repository.UserRepository;
import br.com.fiap.globalsolution.service.TokenService;
public class AuthorizationFilter extends OncePerRequestFilter {

    ApplicationContext context;

    public AuthorizationFilter(){}
    public AuthorizationFilter(ApplicationContext context){
        this.context = context;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");

        // System.out.println(header);

        if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        UserRepository repository = context.getBean(UserRepository.class);
        var service = new TokenService(repository);
        String token = header.substring(7);

        // System.out.println("Token: " + token);

        if (service.validate(token)){
            SecurityContextHolder.getContext().setAuthentication(service.getAuthenticationToken(token));
        }

        filterChain.doFilter(request, response);
    }
    
}
