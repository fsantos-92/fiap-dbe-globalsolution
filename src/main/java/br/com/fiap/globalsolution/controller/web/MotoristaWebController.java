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

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.User;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.TelefoneService;
import br.com.fiap.globalsolution.service.UserService;

@Controller
@RequestMapping("/motorista")
public class MotoristaWebController {
    @Autowired
    MotoristaService motoristaService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

        return new ModelAndView("motorista/index").addObject("motoristas", motoristaService.listAll(pageable));
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Motorista motorista) {
        return "motorista/cadastrar";
    }

    @PostMapping("/cadastrar")
    public ModelAndView criar(@Valid Motorista motorista, BindingResult binding, RedirectAttributes redirect) {
        if(binding.hasErrors()) return new ModelAndView("motorista/cadastrar");

        if(motorista.getId() == null)
        {
            Optional<User> us = userService.getByEmail(motorista.getEmail());
            if(us.isPresent())
            {
                return new ModelAndView("motorista/cadastrar").addObject("errormessage", "E-mail já cadastrado");
            }
        }
        motorista.setPassword(passwordEncoder.encode(motorista.getPassword()));
        User user = motorista.toUser();
        String mensagem = motorista.getId() != null ? "Motorista Atualizado" : "Motorista cadastrado";
        motoristaService.save(motorista);
        userService.save(user);
        redirect.addFlashAttribute("message",mensagem);
        return new ModelAndView("redirect:/motorista");
    }

    @PostMapping("/excluir")
    public String delete(Long id, RedirectAttributes redirect) {
        Optional<Motorista> optional = motoristaService.getById(id);
        Optional<User> opt = userService.getByEmail(optional.get().toUser().getEmail());
        User user = opt.get();
        userService.deleteById(user.getId());
        motoristaService.deleteById(id);
        redirect.addFlashAttribute("message", "Motorista apagado com sucesso");
        return "redirect:/motorista";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id){
        Motorista motorista = motoristaService.getById(id).get();
        return new ModelAndView("motorista/cadastrar").addObject("motorista", motorista);
    }
}
