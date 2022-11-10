package br.com.fiap.globalsolution.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.Role;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.repository.PassageiroRepository;
import br.com.fiap.globalsolution.repository.UserRepository;

@Configuration
public class DatabaseSeed implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PassageiroRepository passageiroRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        Passageiro passageiro = new Passageiro("Admin", "1", "1", "admin@fiap.com.br", passwordEncoder.encode("fiap123"));
        User user = passageiro.toUser().withRole(new Role("ROLE_ADMIN"));

        passageiroRepository.save(passageiro);
        userRepository.save(
            user
        );

        
    }
    
}
