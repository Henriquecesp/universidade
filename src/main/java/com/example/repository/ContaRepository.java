package com.example.repository;

import com.example.models.Conta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends CrudRepository<Conta, Long> {
}
