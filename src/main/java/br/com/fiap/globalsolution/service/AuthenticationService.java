package br.com.fiap.globalsolution.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.repository.UserRepository;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByEmail(username);

        if (user.isPresent()) return user.get();

        throw new UsernameNotFoundException("nome de usuário não encontrado " + username);
    }
    
}
