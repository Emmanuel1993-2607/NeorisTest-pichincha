package com.manager.bankaccount.Implement;

import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.repository.CuentaRepository;
import com.manager.bankaccount.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaImplement implements CuentaService {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    public Cuenta getCuentaById(Long idCuenta) {
        return this.cuentaRepository.findByCuentaId(idCuenta).orElse(null);
    }

    @Override
    public Cuenta guardarCuenta(Cuenta cuenta) {
        return this.cuentaRepository.save(cuenta);
    }

    @Override
    public void eliminarCuenta(Cuenta cuenta) {
        this.cuentaRepository.delete(cuenta);
    }

    @Override
    public void eliminarCuenta(Long idCuenta) {
        Cuenta cuenta = this.getCuentaById(idCuenta);
        if (cuenta != null) {
            this.eliminarCuenta(cuenta);
        }
    }

    @Override
    public Cuenta getCuentaByNumeroCuenta(String numeroCuenta) {
        return this.cuentaRepository.findByNumeroCuenta(numeroCuenta).orElse(null);
    }
}
