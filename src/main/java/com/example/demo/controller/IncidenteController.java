package com.example.demo.controller;

import com.example.demo.entities.IncidenteEntity;
import com.example.demo.service.IncidenteServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IncidenteController {

    @Autowired
    private IncidenteServiceImpl incidenteService;

    // ==================== CREAR INCIDENTE ====================

    @GetMapping("/CrearIncidente")
    public String crearIncidente(Model model) {
        model.addAttribute("titulo", "Formulario de incidente");
        model.addAttribute("objIncidente", new IncidenteEntity());
        return "CrearIncidente";
    }

    @PostMapping("/crear")
    public String guardarIncidente(@Valid @ModelAttribute("objIncidente") IncidenteEntity incidente,
                                   BindingResult result,
                                   Model model,
                                   SessionStatus status) {

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario de incidente");
            return "CrearIncidente";
        }

        incidente.setEstado("abierto");
        incidenteService.save(incidente);
        status.setComplete();

        return "redirect:/ListarIncidentes";
    }

    // ==================== LISTAR INCIDENTES ====================

    @GetMapping("/ListarIncidentes")
    public String listarIncidentes(Model model) {
        List<IncidenteEntity> incidentes = incidenteService.findAll();
        model.addAttribute("titulo", "Lista de Incidentes");
        model.addAttribute("incidentes", incidentes);
        return "ListarIncidentes";
    }

    // ==================== ASIGNAR TÉCNICO ====================

    @GetMapping("/AsignarTecnico")
    public String mostrarAsignarTecnico(Model model) {
        List<IncidenteEntity> incidentes = incidenteService.findAll();
        model.addAttribute("titulo", "Asignar Técnico a Incidentes");
        model.addAttribute("incidentes", incidentes);

        List<String> tecnicos = List.of("pedro", "felipe", "juan", "peter", "rafael");
        model.addAttribute("tecnicos", tecnicos);

        return "AsignarTecnico";
    }

    @PostMapping("/asignar-tecnico/{id}")
    public String asignarTecnico(@PathVariable Integer id,
                                 @RequestParam("tecnico") String tecnico,
                                 RedirectAttributes redirectAttributes) {
        try {
            IncidenteEntity incidente = incidenteService.findById(id);

            if (incidente != null) {
                incidente.setTecnico(tecnico);
                incidenteService.save(incidente);

                redirectAttributes.addFlashAttribute("mensaje", "Técnico asignado exitosamente al incidente #" + id);
                redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Incidente no encontrado");
                redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al asignar técnico: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/AsignarTecnico";
    }

    // ==================== GESTIONAR ESTADO ====================

    @GetMapping("/GestionarEstado")
    public String mostrarGestionarEstado(Model model) {
        List<IncidenteEntity> incidentes = incidenteService.findAll();
        model.addAttribute("titulo", "Gestionar Estado de Incidentes");
        model.addAttribute("incidentes", incidentes);

        List<String> estados = List.of("abierto", "en progreso", "en espera", "cerrado");
        model.addAttribute("estados", estados);

        return "GestionarEstado";
    }

    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstado(@PathVariable Integer id,
                                @RequestParam("estado") String estado,
                                RedirectAttributes redirectAttributes) {
        try {
            IncidenteEntity incidente = incidenteService.findById(id);

            if (incidente != null) {
                incidente.setEstado(estado);
                incidenteService.save(incidente);

                redirectAttributes.addFlashAttribute("mensaje", "Estado actualizado exitosamente para el incidente #" + id);
                redirectAttributes.addFlashAttribute("tipoMensaje", "success");
            } else {
                redirectAttributes.addFlashAttribute("mensaje", "Incidente no encontrado");
                redirectAttributes.addFlashAttribute("tipoMensaje", "error");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensaje", "Error al cambiar estado: " + e.getMessage());
            redirectAttributes.addFlashAttribute("tipoMensaje", "error");
        }

        return "redirect:/GestionarEstado";
    }

    // ==================== REDIRECCIÓN INICIAL ====================

    @GetMapping
    public String redirigirACrear() {
        return "redirect:/";
    }
}