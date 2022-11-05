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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfiguration{

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception{
        http
            // .addFilterAfter(new AuthorizationFilter(), BasicAuthenticationFilter.class)
            .authorizeHttpRequests() 
                // Usuarios
                .antMatchers(HttpMethod.GET, "/api/user/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/user/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/user/**").authenticated()

                // Motoristas
                .antMatchers(HttpMethod.GET, "/api/motorista/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/motorista").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/motorista/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/motorista/**").permitAll()

                // Telefone
                .antMatchers(HttpMethod.GET, "/api/telefone/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/telefone").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/telefone/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/telefone/**").permitAll()

                // Veiculos
                .antMatchers(HttpMethod.GET, "/api/veiculo/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/veiculo").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/veiculo/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/veiculo/**").permitAll()

                // Passageiro
                .antMatchers(HttpMethod.GET, "/api/passageiro/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/passageiro").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/passageiro/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/passageiro/**").permitAll()

                // Corridas
                .antMatchers(HttpMethod.GET, "/api/corrida/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/corrida").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/corrida/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/corrida/**").permitAll()
                
                // Login
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                
                // h2
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers("/css/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
            //.and()
                // .headers().frameOptions().disable()
            // .and()
                // .formLogin()
                
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
