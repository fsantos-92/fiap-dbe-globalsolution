package br.com.fiap.globalsolution.controller.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeWebController {
    
    @GetMapping
    public ModelAndView index(@PageableDefault(size = 2) Pageable pageable) {

        return new ModelAndView("home/index");
    }
}
