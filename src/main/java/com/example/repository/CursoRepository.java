package com.example.repository;

import com.example.models.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends CrudRepository<Curso, Long> {
    Page<Curso> findByUniversidadeId(long universidadeId, Pageable pageable);
    Optional<Curso> findByIdAndUniversidadeId(long id, long universidadeId);
}
