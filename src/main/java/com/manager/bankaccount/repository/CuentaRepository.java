package com.manager.bankaccount.repository;

import java.util.List;
import java.util.Optional;

import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.entity.Cuenta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
        Optional<Cuenta> findByCuentaId(Long cuentaId);

        Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);

}
