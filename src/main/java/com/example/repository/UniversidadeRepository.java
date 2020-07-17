package com.example.repository;

import com.example.models.Universidade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadeRepository extends CrudRepository<Universidade, Long> {
}
