package com.Ejercicio.Ejercicio.controller;

import com.Ejercicio.Ejercicio.domain.Individuo;
import com.Ejercicio.Ejercicio.service.IndividuoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;



@Controller
public class HomeController {

    private final IndividuoService individuoService;

    @Autowired
    public HomeController(IndividuoService individuoService) {
        this.individuoService =individuoService;     
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
    public String guardar(@Valid Individuo individuo, BindingResult resultado) {
        if (resultado.hasErrors()){
            return "form";
        }
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

    @GetMapping("/")
    public String index(Model model, Authentication authentication){
        var lista = individuoService.listarIndividuos();
        model.addAttribute("lista", lista);
        boolean esAdmin = false;
        String usuarioActual = "No autenticado";
        String rolesActuales = "Sin roles";

        if (authentication !=null){
            usuarioActual = authentication.getName();
            rolesActuales = authentication.getAuthorities().toString();

            for (GrantedAuthority authority : authentication.getAuthorities()){
                if (authority.getAuthority().equals("ROLE_ADMIN")) {
                    esAdmin = true;
                    break;
                }
                    
            }
        }

        model.addAttribute("esAdmin", esAdmin);
        model.addAttribute("usuarioActual", usuarioActual);
        model.addAttribute("rolesActuales", rolesActuales);
        return "index";

    }

    @GetMapping("/login")
    public String login() {
        return  "login";
    }
    
    
    
}