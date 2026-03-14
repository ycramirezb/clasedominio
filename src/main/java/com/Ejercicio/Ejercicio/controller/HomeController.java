package com.Ejercicio.Ejercicio.controller;

import com.Ejercicio.Ejercicio.domain.Individuo;
import com.Ejercicio.Ejercicio.service.IndividuoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class HomeController {

    private final IndividuoService individuoService;

    @Autowired
    public HomeController(IndividuoService individuoService) {
        this.individuoService =individuoService;     
    }

    //Lista: trae los registro reales desde Mysql
    @GetMapping("/")
    public String index(Model model) {

        var lista = individuoService.listarIndividuos();
        model.addAttribute("lista", lista);
        return "index";
    }
    
    //DETALLE: trae un individuo por ID desde Mysql
    @GetMapping("/detalle")
    public String detalle(@RequestParam ("id") Integer id, Model model) {

        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        model.addAttribute("individuo", individuo);

        return "detalle";
    }
    
    @GetMapping("/guardarDemo")
    public String guardarDemo() {

        Individuo nuevo = new Individuo();
        nuevo.setNombre("Nuevo");
        nuevo.setApellido("Registro");
        nuevo.setEdad(20);
        nuevo.setEmail("nuevo@net.net");
        nuevo.setTelefono("9999999999");

        individuoService.guardarIndividuo(nuevo);
   
        return "redirect:/";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model ) {
        model.addAttribute("individuo", new Individuo());
        return "form";
    }

    @PostMapping("/guardar")
    public String guardar(Individuo individuo) {
        individuoService.guardarIndividuo(individuo);       
        return "redirect:/";
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam ("id") Integer id) {
        individuoService.eliminarIndividuo(id);

        return "redirect:/";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") Integer id, Model model) {
        Individuo individuo = individuoService.buscarIndividuoPorId(id);
        model.addAttribute("individuo", individuo);
        return "/form";
    }
    
    
    
    
    

    
}