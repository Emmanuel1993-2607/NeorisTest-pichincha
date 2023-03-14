package com.manager.bankaccount.service;

import com.manager.bankaccount.entity.Cuenta;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaService {
    Cuenta getCuentaById(Long idCuenta);

    Cuenta guardarCuenta(Cuenta cuenta);

    void eliminarCuenta(Cuenta cuenta);

    void eliminarCuenta(Long idCuenta);

    Cuenta getCuentaByNumeroCuenta(String numeroCuenta);
}
