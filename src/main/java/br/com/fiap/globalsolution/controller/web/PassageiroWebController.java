package br.com.fiap.globalsolution.controller.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.PassageiroService;
import br.com.fiap.globalsolution.service.UserService;

@Controller
@RequestMapping("/passageiro")
public class PassageiroWebController {
    @Autowired
    PassageiroService passageiroService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView index() {

        return new ModelAndView("passageiro/index").addObject("passageiros", passageiroService.listAll());
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Passageiro passageiro) {
        
        return "passageiro/cadastrar";
    }

    @PostMapping("/cadastrar")
    public ModelAndView criar(@Valid Passageiro passageiro, BindingResult binding, RedirectAttributes redirect) {
        if(binding.hasErrors()) return new ModelAndView("passageiro/cadastrar");

        if(passageiro.getId() == null)
        {
            Optional<User> us = userService.getByEmail(passageiro.getEmail());
            if(us.isPresent())
            {
                redirect.addFlashAttribute("message","E-mail já cadastrado");
                return new ModelAndView("passageiro/cadastrar").addObject("errormessage", "E-mail já cadastrado");
            }
            
        
        }
        passageiro.setPassword(passwordEncoder.encode(passageiro.getPassword()));
        User user = passageiro.toUser();
        String mensagem = passageiro.getId() != null ? "Passageiro Atualizado" : "Passageiro cadastrado";
        userService.save(user);
        passageiroService.save(passageiro);
        redirect.addFlashAttribute("message",mensagem);
        return new ModelAndView("redirect:/passageiro");
    }

    @PostMapping("/excluir")
    public String delete(Long id, RedirectAttributes redirect) {
        Optional<Passageiro> optional = passageiroService.getById(id);
        Optional<User> opt = userService.getByEmail(optional.get().getEmail());
        User user = opt.get();
        userService.deleteById(user.getId());
        passageiroService.deleteById(id);
        redirect.addFlashAttribute("message", "Passageiro apagado com sucesso");
        return "redirect:/passageiro";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id){
        Passageiro passageiro = passageiroService.getById(id).get();
        return new ModelAndView("passageiro/cadastrar").addObject("passageiro", passageiro);
    }
}
