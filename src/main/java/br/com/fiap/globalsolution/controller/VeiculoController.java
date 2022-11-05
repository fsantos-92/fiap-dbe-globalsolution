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

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Veiculo;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.VeiculoService;

@RestController
@RequestMapping("/api/veiculo")
public class VeiculoController {
    
    @Autowired
    VeiculoService service;

    @Autowired
    MotoristaService motoristaService;
    
    @GetMapping
    public Page<Veiculo> index( @PageableDefault(size = 10, sort = "id") Pageable pageable){
        return service.listAll(pageable);
    }

    @PostMapping
    public ResponseEntity<Veiculo> create(@RequestBody @Valid Veiculo veiculo){
        service.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> destroy(@PathVariable Long id){
        Optional<Veiculo> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("{id}")
    public ResponseEntity<Veiculo> show(@PathVariable Long id){
        Optional<Veiculo> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Veiculo veiculo = optional.get();
        return ResponseEntity.ok(veiculo);
    }



    @PutMapping("{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody @Valid Veiculo newVeiculo){
        Optional<Veiculo> optional = service.getById(id);
        if (optional.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var veiculo = optional.get();
        BeanUtils.copyProperties(newVeiculo, veiculo, new String[]{"id"});
        service.save(veiculo);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping("motorista/{id}")
    public ResponseEntity<List<Veiculo>> listByMotorista(@PathVariable Long id){
        Optional<Motorista> optional = motoristaService.getById(id);
        if (optional.isEmpty()) return ResponseEntity.notFound().build();
        Motorista motorista = optional.get();
        List<Veiculo> veiculos = service.findByMotorista(motorista);
        return ResponseEntity.ok(veiculos);
    }
}
