package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {



    /**
     * Maneja la solicitud GET a la URL /index (después del login)
     * Retorna la plantilla de Thymeleaf llamada "index.html".
     */
    @GetMapping("/index")
    public String showIndexPage() {
        // Retorna el nombre del archivo de plantilla (sin la extensión .html)
        return "index";
    }
}