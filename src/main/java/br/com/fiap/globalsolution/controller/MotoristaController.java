package br.com.fiap.globalsolution.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.UserService;

@RestController
@RequestMapping("/api/motorista")
@CrossOrigin("*")
public class MotoristaController {
    @Autowired
    UserService userService;

    @Autowired
    MotoristaService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public Page<Motorista> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Motorista motorista){
        Optional<User> us = userService.getByEmail(motorista.getEmail());
        if(us.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");

        motorista.setPassword(passwordEncoder.encode(motorista.getPassword()));
        User user = motorista.toUser();
        user.setMotorista(true);
        userService.save(user);
        service.save(motorista);
        
        
        return ResponseEntity.status(HttpStatus.CREATED).body(motorista);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Motorista> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Optional<User> opt = userService.getByEmail(optional.get().toUser().getEmail());
        User user = opt.get();
        userService.deleteById(user.getId());
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Motorista> show(@PathVariable Long id){
        Optional<Motorista> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Motorista motorista = optional.get();
        return ResponseEntity.ok(motorista);
    }

    @PutMapping("{id}")
    public ResponseEntity<Motorista> update(@PathVariable Long id, @RequestBody @Valid Motorista newMotorista){
        Optional<Motorista> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var motorista = optional.get();
        Optional<User> opt = userService.getByEmail(motorista.getEmail());
        BeanUtils.copyProperties(newMotorista, motorista, new String [] {"id", "password"} );
        service.save(motorista);

        User user = motorista.toUser();
        user.setId(opt.get().getId());
        userService.save(user);

        return ResponseEntity.ok(motorista);
    }
}
