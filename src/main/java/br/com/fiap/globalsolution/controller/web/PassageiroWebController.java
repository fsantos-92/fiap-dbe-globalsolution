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

import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.service.PassageiroService;

@Controller
@RequestMapping("/passageiro")
public class PassageiroWebController {
    @Autowired
    PassageiroService passageiroService;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

        return new ModelAndView("passageiro/index").addObject("passageiros", passageiroService.listAll(pageable));
    }

    @GetMapping("/cadastrar")
    public String cadastrar(Passageiro passageiro) {
        return "passageiro/cadastrar";
    }

    @PostMapping("/cadastrar")
    public String criar(@Valid Passageiro passageiro, BindingResult binding, RedirectAttributes redirect) {
        if(binding.hasErrors()) return "passageiro/cadastrar";
        String mensagem = passageiro.getId() != null ? "Passageiro Atualizado" : "Passageiro cadastrado";
        passageiroService.save(passageiro);
        redirect.addFlashAttribute("message",mensagem);
        return "redirect:/passageiro";
    }

    @PostMapping("/excluir")
    public String delete(Long id, RedirectAttributes redirect) {
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
