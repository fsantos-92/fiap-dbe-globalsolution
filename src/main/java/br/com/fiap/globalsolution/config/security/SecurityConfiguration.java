package br.com.fiap.globalsolution.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.fiap.globalsolution.service.TokenService;

@Configuration
public class SecurityConfiguration{

    @Autowired ApplicationContext context;

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

                // Passageiro
                .antMatchers(HttpMethod.GET, "/api/passageiro/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/passageiro").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/passageiro/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/passageiro/**").authenticated()

                // Corridas
                .antMatchers(HttpMethod.GET, "/api/corrida/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/corrida").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/corrida/**").authenticated()
                .antMatchers(HttpMethod.PUT, "/api/corrida/**").authenticated()
                
                // Login
                .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
                
                // h2
                .antMatchers("/h2-console/**").permitAll()
/* 
                // web
                // home
                .antMatchers(HttpMethod.GET, "/home/**").authenticated()
                // corridas
                .antMatchers(HttpMethod.GET, "/corrida").authenticated()
                .antMatchers(HttpMethod.GET, "/corrida/**").authenticated()
                .antMatchers(HttpMethod.POST, "/corrida/excluir/**").authenticated()
                .antMatchers(HttpMethod.POST, "/corrida/cadastrar").authenticated()

                // motorista
                .antMatchers(HttpMethod.GET, "/motorista").authenticated()
                .antMatchers(HttpMethod.GET, "/motorista/**").authenticated()
                .antMatchers(HttpMethod.POST, "/motorista/excluir/**").authenticated()
                .antMatchers(HttpMethod.POST, "/motorista/cadastrar").authenticated()

                // veiculo
                .antMatchers(HttpMethod.GET, "/veiculo").authenticated()
                .antMatchers(HttpMethod.GET, "/veiculo/**").authenticated()
                .antMatchers(HttpMethod.POST, "/veiculo/excluir/**").authenticated()
                .antMatchers(HttpMethod.POST, "/veiculo/cadastrar").authenticated()
                .antMatchers(HttpMethod.POST, "/veiculo/cadastrar/**").authenticated()

                // passageiro
                .antMatchers(HttpMethod.GET, "/passageiro").authenticated()
                .antMatchers(HttpMethod.GET, "/passageiro/**").authenticated()
                .antMatchers(HttpMethod.POST, "/passageiro/excluir/**").authenticated()
                .antMatchers(HttpMethod.POST, "/passageiro/cadastrar").authenticated()

*/
                .antMatchers("/css/**").permitAll()

                .anyRequest().denyAll()
            .and()
                .csrf().disable()
            
                // .headers().frameOptions().disable()
            // .and()
                .addFilterBefore(new AuthorizationFilter(context), UsernamePasswordAuthenticationFilter.class)
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
