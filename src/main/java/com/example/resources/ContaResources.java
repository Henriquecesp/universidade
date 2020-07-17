package com.example.resources;

import com.example.ResourceNotFoundException;
import com.example.models.Conta;
import com.example.models.Curso;
import com.example.repository.ContaRepository;
import com.example.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/universidade")
public class ContaResources {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/{universidadeId}/curso/{cursoId}/conta")
    public Optional<Curso> getContaByUniversidadeAndCursoId(@PathVariable (value = "universidadeId") long universidadeId,
                                                            @PathVariable (value = "cursoId") long cursoId){
        return cursoRepository.findByIdAndUniversidadeId(universidadeId, cursoId);
    }

    @PostMapping("/{universidadeId}/curso/{cursoId}/conta")
    public Conta cadastrarConta(@PathVariable (value = "universidadeId") long universidadeId,
                                @PathVariable (value = "cursoId") long cursoId,
                                @RequestBody Conta conta){
        return cursoRepository.findByIdAndUniversidadeId(cursoId, universidadeId).map(curso -> {
            conta.setCurso(curso);
            return contaRepository.save(conta);
        }).orElseThrow(() -> new ResourceNotFoundException("Universidade ou curso nao encontrado"));
    }

    @PutMapping("/deposito/{contaId}")
    public void depositar(
            @PathVariable (value = "contaId") long contaId,
            @RequestBody Conta conta
    ){
            contaRepository.findById(contaId).map(contaPorId -> {
                double novoSaldo = contaPorId.getSaldo() + conta.getSaldo();
                if(contaPorId.getSaldo() + conta.getSaldo() < 0){
                    throw new ResourceNotFoundException("Saldo invalido");
                }
                contaPorId.setSaldo(novoSaldo);
                return contaRepository.save(contaPorId);
            }).orElseThrow(() -> new ResourceNotFoundException("Deu ruim"));
    }
}
