package br.com.fiap.globalsolution.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @GetMapping
    public Page<User> index( @PageableDefault(size = 10, sort = "name") Pageable pageable){
        return service.listAll(pageable);
    }    

}
