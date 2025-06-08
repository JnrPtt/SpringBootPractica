package com.example.demo.service;

import com.example.demo.dto.TareaResponseDTO;
import com.example.demo.dto.TareaRequestDTO;
import com.example.demo.model.Tarea;
import com.example.demo.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<TareaResponseDTO> obtenerTareas() {
        List<Tarea> tareas = tareaRepository.findAll();
        return tareas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public TareaResponseDTO crearTarea(TareaRequestDTO tareaRequest) {
        Tarea tarea = new Tarea();
        tarea.setTitulo(tareaRequest.getTitulo());
        tarea.setDescripcion(tareaRequest.getDescripcion());

        Tarea tareaGuardada= tareaRepository.save(tarea);
        return convertirADTO(tareaGuardada);
    }

    public TareaResponseDTO actualizarTarea(Long id, TareaRequestDTO tareaRequest) {
        return tareaRepository.findById(id)
                .map(tarea -> {
                    tarea.setTitulo(tareaRequest.getTitulo());
                    tarea.setDescripcion(tareaRequest.getDescripcion());
                    Tarea tareaActualizada = tareaRepository.save(tarea);
                    return convertirADTO(tareaActualizada);
                })
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada con id: " + id));
    }

    public void eliminarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    private TareaResponseDTO convertirADTO(Tarea tarea) {
        return new TareaResponseDTO(tarea.getId(), tarea.getTitulo(), tarea.getDescripcion());
    }
}
