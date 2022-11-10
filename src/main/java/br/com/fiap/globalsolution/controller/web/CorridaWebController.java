package br.com.fiap.globalsolution.controller.web;

import java.util.List;

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

import br.com.fiap.globalsolution.model.Corrida;
import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Passageiro;
import br.com.fiap.globalsolution.model.Veiculo;
import br.com.fiap.globalsolution.service.CorridaService;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.PassageiroService;
import br.com.fiap.globalsolution.service.VeiculoService;

@Controller
@RequestMapping("/corrida")
public class CorridaWebController {
    @Autowired
    CorridaService corridaService;

    @Autowired
    PassageiroService passageiroService;

    @Autowired
    MotoristaService motoristaService;

    @Autowired
    VeiculoService veiculoService;

    @GetMapping
    public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

        return new ModelAndView("/corrida/index").addObject("corridas", corridaService.listAll(pageable));
    }

    @PostMapping("/excluir")
    public String delete(Long id, RedirectAttributes redirect) {
        corridaService.deleteById(id);
        redirect.addFlashAttribute("message", "Corrida apagado com sucesso");
        return "redirect:/corrida";
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(Corrida corrida) {
        List<Motorista> motoristas = motoristaService.listAll();
        List<Passageiro> passageiros = passageiroService.listAll();
        List<Veiculo> veiculos = veiculoService.listAll();
        return new ModelAndView("/corrida/cadastrar").addObject("motoristas", motoristas).addObject("passageiros", passageiros).addObject("veiculos", veiculos);
    }

    @PostMapping("/cadastrar")
    public ModelAndView criar(@Valid Corrida corrida, BindingResult binding, RedirectAttributes redirect) {
        if(binding.hasErrors()){
            List<Motorista> motoristas = motoristaService.listAll();
            List<Passageiro> passageiros = passageiroService.listAll();
            List<Veiculo> veiculos = veiculoService.listAll();
            return new ModelAndView("/corrida/cadastrar").addObject("motoristas", motoristas).addObject("passageiros", passageiros).addObject("veiculos", veiculos);
        } 
        String mensagem = corrida.getId() != null ? "Corrida Atualizada" : "Corrida cadastrada";
        corridaService.save(corrida);
        redirect.addFlashAttribute("message",mensagem);
        return new ModelAndView("redirect:/corrida/cadastrar");

    }
    @GetMapping("{id}")
    public ModelAndView edit(@PathVariable Long id){
        List<Motorista> motoristas = motoristaService.listAll();
        List<Passageiro> passageiros = passageiroService.listAll();
        List<Veiculo> veiculos = veiculoService.listAll();
        Corrida corrida = corridaService.getById(id).get();
        return new ModelAndView("/corrida/cadastrar").addObject("motoristas", motoristas).addObject("passageiros", passageiros).addObject("veiculos", veiculos).addObject("corrida", corrida);
    }
}
