package com.example.resources;

import com.example.ResourceNotFoundException;
import com.example.models.Universidade;
import com.example.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.repository.UniversidadeRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/universidade")
public class UniversidadeResources {

    private final UniversidadeRepository universidadeRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    public UniversidadeResources(UniversidadeRepository universidadeRepository) {
        this.universidadeRepository = universidadeRepository;
    }

    @GetMapping(produces = "application/json")
    public @ResponseBody Iterable<Universidade> ListaUniversidades () {
        Iterable<Universidade> listaUniversidades = universidadeRepository.findAll();
        return listaUniversidades;
    }

    @GetMapping("/{id}")
    public Universidade getUniversidadeById(@PathVariable (value = "id") long id){
        return universidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Universidade nao encontrada: " + id));
    }

    @PostMapping()
    public Universidade cadastraUniversidade(@RequestBody Universidade universidade){
        return universidadeRepository.save(universidade);
    }

    @DeleteMapping("/{id}")
    void deletaUniversidade(@PathVariable Long id){
        universidadeRepository.deleteById(id);
    }
}
