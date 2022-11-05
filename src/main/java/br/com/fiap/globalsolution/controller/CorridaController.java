package br.com.fiap.globalsolution.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.Corrida;
import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.service.PassageiroService;
import br.com.fiap.globalsolution.service.CorridaService;
import br.com.fiap.globalsolution.service.MotoristaService;

@RestController
@RequestMapping("/api/corrida")
public class CorridaController {
    @Autowired
    CorridaService service;

    @Autowired
    MotoristaService motoristaService;

    @Autowired
    PassageiroService passageiroService;
    
    @GetMapping
    public Page<Corrida> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Corrida> create(@RequestBody @Valid Corrida corrida){
        service.save(corrida);
        return ResponseEntity.status(HttpStatus.CREATED).body(corrida);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Corrida> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Corrida> show(@PathVariable Long id){
        Optional<Corrida> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Corrida corrida = optional.get();
        return ResponseEntity.ok(corrida);
    }



    @PutMapping("{id}")
    public ResponseEntity<Corrida> update(@PathVariable Long id, @RequestBody @Valid Corrida newCorrida){
        Optional<Corrida> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var corrida = optional.get();
        BeanUtils.copyProperties(newCorrida, corrida, new String[]{"id"});
        service.save(corrida);
        return ResponseEntity.ok(corrida);
    }

    @GetMapping("motorista/{id}")
    public ResponseEntity<List<Corrida>> listByMotorista(@PathVariable Long id){
        Optional<Motorista> optional = motoristaService.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Motorista motorista = optional.get();
        List<Corrida> corridas = service.findByMotorista(motorista);
        return ResponseEntity.ok(corridas);
    }

    @GetMapping("passageiro/{id}")
    public ResponseEntity<List<Corrida>> listByPassageiro(@PathVariable Long id){
        Optional<Passageiro> optional = passageiroService.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Passageiro passageiro = optional.get();
        List<Corrida> corridas = service.findByPassageiro(passageiro);
        return ResponseEntity.ok(corridas);
    }
}
