package com.manager.bankaccount.repository;

import java.util.Optional;

import com.manager.bankaccount.entity.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByClienteid(Long clienteId);

    Optional<Cliente> findByIdentificacion(String identificacion);

}
