package br.com.fiap.globalsolution.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.fiap.globalsolution.dto.UserDto;
import br.com.fiap.globalsolution.dto.UserLoginDto;
import br.com.fiap.globalsolution.model.JwtToken;
import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.AuthenticationService;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.PassageiroService;
import br.com.fiap.globalsolution.service.UserService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${globalsolution.jwt.secret}")
    private String secret;

    @Autowired
    AuthenticationService service;

    @Autowired
    PassageiroService passageiroService;

    @Autowired
    MotoristaService motoristaService;
    
    @PostMapping
    public ResponseEntity<Object> auth(@RequestBody User user){
        try{
            Authentication authentication = 
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            authenticationManager.authenticate(authentication);
            Date issuedAt = new Date();
            Date expiresAt = new Date(issuedAt.getTime() + 60_000 );
            String token = JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(Algorithm.HMAC512(secret)
            );
            long id = 0;
            User newUser = (User)service.loadUserByUsername(user.getEmail());
            if(newUser.isMotorista())
                id = motoristaService.findByEmail(newUser.getEmail()).getId();
            else {
                Passageiro passageiro = passageiroService.findByEmail(newUser.getEmail());
                if(passageiro != null)
                    id = passageiro.getId();
            }
            return ResponseEntity.ok(new UserLoginDto(id, newUser.getName(), user.getEmail(), newUser.isMotorista(), new JwtToken(token, "Bearer")));
        }catch(AuthenticationException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

}
