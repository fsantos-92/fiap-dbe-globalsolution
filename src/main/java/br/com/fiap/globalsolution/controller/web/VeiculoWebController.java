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

import br.com.fiap.globalsolution.model.Motorista;
import br.com.fiap.globalsolution.model.Veiculo;
import br.com.fiap.globalsolution.service.MotoristaService;
import br.com.fiap.globalsolution.service.VeiculoService;

@Controller
@RequestMapping("/veiculo")
public class VeiculoWebController {
    @Autowired
    VeiculoService veiculoService;

    @Autowired
    MotoristaService motoristaService;

    // @GetMapping
    // public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

    //     return new ModelAndView("/veiculo/index").addObject("veiculos", veiculoService.listAll(pageable));
    // }

    @GetMapping("/cadastrar/{motoristaid}")
    public ModelAndView cadastrar(Veiculo veiculo, @PathVariable Long motoristaid) {
        Motorista motorista = motoristaService.getById(motoristaid).get();
        veiculo.setMotorista(motorista);
        return new ModelAndView("veiculo/cadastrar").addObject("motorista", motorista);
    }

    @PostMapping("/cadastrar/{motoristaid}")
    public ModelAndView criar(@Valid Veiculo veiculo, BindingResult binding, RedirectAttributes redirect, @PathVariable Long motoristaid) {
        Motorista motorista = motoristaService.getById(motoristaid).get();
        //veiculo.setMotorista(motorista);
        if(binding.hasErrors()) {
            return new ModelAndView("veiculo/cadastrar").addObject("motorista", motorista);
        }
        String mensagem = veiculo.getId() != null ? "Veiculo Atualizado" : "Veiculo cadastrado";
        veiculoService.save(veiculo);
        redirect.addFlashAttribute("message",mensagem);
        List<Veiculo> veiculos = veiculoService.findByMotorista(motorista);
        return new ModelAndView("redirect:/veiculo/index/" + motorista.getId()).addObject("motorista", motorista).addObject("veiculos", veiculos);
    }

    @PostMapping("/excluir")
    public ModelAndView delete(Long id, RedirectAttributes redirect) {
        Veiculo veiculo = veiculoService.getById(id).get();
        Motorista motorista = motoristaService.getById(veiculo.getMotorista().getId()).get();
        veiculoService.deleteById(id);
        redirect.addFlashAttribute("message", "Veiculo apagado com sucesso");
        return new ModelAndView("redirect:/veiculo/index/" + motorista.getId()).addObject("motorista", motorista);
    }

    @GetMapping("/index/{id}")
    public ModelAndView index(@PathVariable Long id){
        Motorista motorista = motoristaService.getById(id).get();
        List<Veiculo> veiculos = veiculoService.findByMotorista(motorista);
        return new ModelAndView("veiculo/index").addObject("veiculos", veiculos).addObject("motorista", motorista);
    }

    @GetMapping("/editar/{id}")
    public ModelAndView edit(@PathVariable Long id){
        Veiculo veiculo = veiculoService.getById(id).get();
        Motorista motorista = motoristaService.getById(veiculo.getMotorista().getId()).get();
        return new ModelAndView("veiculo/cadastrar").addObject("motorista", motorista).addObject("veiculo", veiculo);
        //return new ModelAndView("/cadastrar/index").addObject("veiculo", veiculo).addObject("motorista", motorista);
    }
}
