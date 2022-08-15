package com.API.gestionempleados.Controllers;

import com.API.gestionempleados.Entities.Empleado;
import com.API.gestionempleados.Repositories.EmpleadoRepository;
import com.API.gestionempleados.Services.EmpleadoServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class EmpleadoController {

    @Autowired
    private EmpleadoServiceInt empleadoService;

    @GetMapping ({"/","/list","/listar"}) //Or (Pageable page) -> ver configuración del archivo properties
    public String listarEmpleados(@RequestParam(required = false, defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "5") int pageSize, Model model) {
        Pageable page= PageRequest.of(pageNumber,pageSize);
        Page<?> listaPaginadaEmpleados= empleadoService.findAll(page);
        model.addAttribute("listaEmpleados", listaPaginadaEmpleados);

        return "listar";
    }

    @GetMapping ("/registrarempleado")
    public String mostrarFormularioRegistroEmpleado(Map<String,Object> modelo) {
        modelo.put("empleado", new Empleado());
        modelo.put("titulo", "Registro de empleado"); //irrelevante
        return "registrarempleado";
    }

    @PostMapping("/registrarempleado")
    public String registrarEmpleado(@Valid Empleado empleado, BindingResult result, Model model, RedirectAttributes flash, SessionStatus status) {
        if (result.hasErrors()) {
            model.addAttribute("titulo", "Registro de empleado");
            return "registrarempleado";
        }
        String mensaje= ((empleado.getId() != null) ? "Empleado editado con exito" : "Empleado registrado con exito");
        empleadoService.save(empleado);
        status.setComplete();
        flash.addFlashAttribute("success", mensaje);

        return "redirect:/listar";
    }

    @GetMapping ("/editarempleado/{id}")
    public String editarEmpleado(@PathVariable(value = "id") Long id, Map<String,Object> model, RedirectAttributes flash) {
        Empleado empleado= ((id > 0) ? empleadoService.findOne(id) : null);

        if (empleado==null) {
            flash.addFlashAttribute("error","El empleado no existe en la base de datos");
            return "redirect:/listar";
        }
        model.put("titulo", "Edición de empleado");
        model.put("empleado", empleado);

        return "editarempleado";
    }

    @DeleteMapping ("/eliminarempleado/{id}")
    public String eliminarEmpleado(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
        if (empleadoService.findOne(id) != null) {
            empleadoService.delete(id);
            flash.addFlashAttribute("success", "Empleado eliminado con exito");
        }
        else
            flash.addFlashAttribute("success", "El empleado no existe en la base de datos");

        return "redirect:/listar";
    }
}
