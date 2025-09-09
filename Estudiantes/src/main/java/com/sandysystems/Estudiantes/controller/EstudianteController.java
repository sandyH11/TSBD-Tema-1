package com.sandysystems.Estudiantes.controller;

import com.sandysystems.Estudiantes.entity.Estudiante;
import com.sandysystems.Estudiantes.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @GetMapping
    public List<Estudiante> getAllEstudiantes() {
        return estudianteRepository.findAll();
    }

    @PostMapping
    public Estudiante createEstudiante(@RequestBody Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    // Endpoint adicional de ejemplo: obtener por id
    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> getEstudianteById(@PathVariable Long id) {
        return estudianteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint de actualización simple
    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> updateEstudiante(@PathVariable Long id, @RequestBody Estudiante updated) {
        return estudianteRepository.findById(id)
                .map(estudiante -> {
                    estudiante.setNombre(updated.getNombre());
                    estudiante.setEdad(updated.getEdad());
                    estudiante.setCarrera(updated.getCarrera());
                    estudiante.setEmail(updated.getEmail());
                    return ResponseEntity.ok(estudianteRepository.save(estudiante));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint de borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEstudiante(@PathVariable Long id) {
        if (estudianteRepository.existsById(id)) {
            estudianteRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}