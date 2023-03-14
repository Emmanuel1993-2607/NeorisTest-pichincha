package com.manager.bankaccount.dto;

import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.entity.Cuenta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CuentaDto {
    private Long cuentaId;

    private String numeroCuenta;

    private String tipoCuenta;

    private BigDecimal saldoInicial;

    private BigDecimal saldo;

    private String estado;

    private Cliente cliente;

    public CuentaDto asignarCuentaId(Long cuentaId) {
        this.cuentaId = cuentaId;
        return this;
    }

    public CuentaDto asignarNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
        return this;
    }

    public CuentaDto asignarTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
        return this;
    }

    public CuentaDto asignarSaldoInicial(BigDecimal saldoInicial) {
        this.saldoInicial = saldoInicial;
        return this;
    }

    public CuentaDto asignarSaldo(BigDecimal saldo) {
        this.saldo = saldo;
        return this;
    }

    public CuentaDto asignarEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public CuentaDto asignarCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public Cuenta build() {
        return new Cuenta(this);
    }

}
