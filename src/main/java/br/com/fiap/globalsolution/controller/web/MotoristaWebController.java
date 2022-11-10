package br.com.fiap.globalsolution.controller.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.TelefoneService;

@Controller
@RequestMapping("/motorista")
public class MotoristaWebController {
    @Autowired
    MotoristaService motoristaService;

    @Autowired
    TelefoneService telefoneService;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

        return new ModelAndView("/motorista/index").addObject("motoristas", motoristaService.listAll(pageable));
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Motorista motorista) {
        return "/motorista/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String criar(@Valid Motorista motorista, BindingResult binding, RedirectAttributes redirect) {
        if(binding.hasErrors()) return "motorista/cadastrar";
        motoristaService.save(motorista);
        String mensagem = motorista.getId() != null ? "Motorista Atualizado" : "Motorista cadastrado";
        redirect.addFlashAttribute("message",mensagem);
        return "redirect:/motorista";
    }

    @PostMapping("/excluir")
    public String delete(Long id, RedirectAttributes redirect) {
        motoristaService.deleteById(id);
        redirect.addFlashAttribute("message", "Motorista apagado com sucesso");
        return "redirect:/motorista";
    }

    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id){
        Motorista motorista = motoristaService.getById(id).get();
        return new ModelAndView("/motorista/cadastrar").addObject("motorista", motorista);
    }
}
