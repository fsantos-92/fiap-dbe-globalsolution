package br.com.fiap.globalsolution.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.PassageiroService;
import br.com.fiap.globalsolution.service.UserService;

@RestController
@RequestMapping("/api/passageiro")
@CrossOrigin("*")
public class PassageiroController {
    @Autowired
    PassageiroService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping
    public Page<Passageiro> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody @Valid Passageiro passageiro){
        Optional<User> us = userService.getByEmail(passageiro.getEmail());
        if(us.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("E-mail já cadastrado");
        
        passageiro.setPassword(passwordEncoder.encode(passageiro.getPassword()));
        User user = passageiro.toUser();
        userService.save(user);
        service.save(passageiro);
        return ResponseEntity.status(HttpStatus.CREATED).body(passageiro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Passageiro> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Optional<User> opt = userService.getByEmail(optional.get().toUser().getEmail());
        User user = opt.get();
        userService.deleteById(user.getId());
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Passageiro> show(@PathVariable Long id){
        Optional<Passageiro> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Passageiro passageiro = optional.get();
        return ResponseEntity.ok(passageiro);
    }

    @PutMapping("{id}")
    public ResponseEntity<Passageiro> update(@PathVariable Long id, @RequestBody @Valid Passageiro newPassageiro){
        Optional<Passageiro> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        Passageiro passageiro = optional.get();
        Optional<User> opt = userService.getByEmail(passageiro.getEmail());
        BeanUtils.copyProperties(newPassageiro, passageiro, new String [] {"id", "password"} );
        service.save(passageiro);

        User user = passageiro.toUser();
        user.setId(opt.get().getId());
        userService.save(user);

        return ResponseEntity.ok(passageiro);
    }
}
