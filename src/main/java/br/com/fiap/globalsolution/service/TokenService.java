package br.com.fiap.globalsolution.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.repository.UserRepository;

@Service
public class TokenService {
    
    @Autowired
    UserRepository repository;

    // @Value("${globalsolution.jwt.secret}")
    // private String secret;
    String secret = "fafslpq$783@!475";

    public TokenService(){}
    public TokenService(UserRepository repository){
        this.repository = repository;
    }

    public boolean validate(String token) {
        System.out.println(secret);
        try{
            JWT.require(Algorithm.HMAC512(secret)).build().verify(token);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

    }

    public Authentication getAuthenticationToken(String token) {
        String email = JWT.require(Algorithm.HMAC512(secret)).build().verify(token).getSubject();
        Optional<User> optional = repository.findByEmail(email);
        if (optional.isEmpty()) return null;
        var user = optional.get();
        Authentication authentication = 
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        
        return authentication;
    }

        
    
}
