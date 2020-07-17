package com.example.resources;

import com.example.ResourceNotFoundException;
import com.example.models.Curso;
import com.example.repository.CursoRepository;
import com.example.repository.UniversidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/universidade")
public class CursoResources {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UniversidadeRepository universidadeRepository;

    @GetMapping("/{universidadeId}/curso")
    public Page<Curso> getAllCursosByUniversidadeId(@PathVariable (value = "universidadeId") long universidadeId, Pageable pageable){
        return cursoRepository.findByUniversidadeId(universidadeId, pageable);
    }

    @PostMapping("/{universidadeId}/curso")
    public Curso createCurso(@PathVariable (value = "universidadeId") long universidadeId,
                             @RequestBody Curso curso){
        return universidadeRepository.findById(universidadeId).map(universidade -> {
            curso.setUniversidade(universidade);
            return cursoRepository.save(curso);
        }).orElseThrow(() -> new ResourceNotFoundException("Universidade nao encontrada: " + universidadeId));
    }

    @DeleteMapping("/{universidadeId}/curso/{cursoId}")
    public ResponseEntity<?> deleteCurso(@PathVariable (value = "universidadeId") long universidadeId,
                                         @PathVariable (value = "cursoId") long cursoId){
        return cursoRepository.findByIdAndUniversidadeId(cursoId, universidadeId).map(curso -> {
            cursoRepository.delete(curso);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Curso nao encontrada: " + cursoId + " e universidade " + universidadeId));
    }
}
