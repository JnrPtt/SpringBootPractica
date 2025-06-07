package com.example.demo.controller;

import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {

    private final TareaRepository tareaRepository;

    public TareaController(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareaRepository.findAll();
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable Long id, @RequestBody Tarea tareaActualizada) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaActualizada.getTitulo());
                    tarea.setDescripcion(tareaActualizada.getDescripcion());
                    return tareaRepository.save(tarea);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    @DeleteMapping("/{id}")
    public void eliminarTarea(@PathVariable Long id) {
        tareaRepository.deleteById(id);
    }
}
