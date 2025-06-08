package com.example.demo.controller;

import com.example.demo.dto.TareaRequestDTO;
import com.example.demo.dto.TareaResponseDTO;
import com.example.demo.service.TareaService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<TareaResponseDTO> obtenerTareas() {
        return tareaService.obtenerTareas();
    }

    @PostMapping
    public TareaResponseDTO crearTarea(@Valid @RequestBody TareaRequestDTO tareaRequest) {
        return tareaService.crearTarea(tareaRequest);
    }

    @PutMapping("/{id}")
    public TareaResponseDTO actualizarTarea(@PathVariable Long id, @Valid @RequestBody TareaRequestDTO tareaRequest) {
        return tareaService.actualizarTarea(id, tareaRequest);
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
    }
}
