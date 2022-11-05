package br.com.fiap.globalsolution.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
            .authorizeHttpRequests() 
                // Usuarios
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()

                // Motoristas
                .antMatchers(HttpMethod.GET, "/api/motorista/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/motorista").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/motorista/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/motorista/**").authenticated()

                // Telefone
                .antMatchers(HttpMethod.GET, "/api/telefone/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/telefone").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/telefone/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/telefone/**").authenticated()

                // Veiculos
                .antMatchers(HttpMethod.GET, "/api/veiculo/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/veiculo").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/veiculo/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/veiculo/**").authenticated()

                // Clientes
                .antMatchers(HttpMethod.GET, "/api/cliente/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/cliente").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/cliente/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/cliente/**").authenticated()

                // Corridas
                .antMatchers(HttpMethod.GET, "/api/corrida/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/corrida").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/corrida/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/corrida/**").authenticated()
                
                // Login
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                
                // h2
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers("/css/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
            //.and()
                .headers().frameOptions().disable()
            .and()
                .formLogin()
                
        ;        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }    
}
