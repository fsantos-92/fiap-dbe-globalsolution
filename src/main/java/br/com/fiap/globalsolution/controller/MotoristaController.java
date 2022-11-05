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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.service.MotoristaService;

@RestController
@RequestMapping("/api/motorista")
public class MotoristaController {
    @Autowired
    MotoristaService service;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public Page<Motorista> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Motorista> create(@RequestBody @Valid Motorista motorista){
        System.out.println(motorista);
        motorista.setPassword(passwordEncoder.encode(motorista.getPassword()));
        service.save(motorista);
        return ResponseEntity.status(HttpStatus.CREATED).body(motorista);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Motorista> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
        BeanUtils.copyProperties(newMotorista, motorista, new String [] {"id", "password"} );
        service.save(motorista);
        return ResponseEntity.ok(motorista);
    }
}
