package com.manager.bankaccount.dto;

import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.entity.Movimiento;
import com.manager.bankaccount.exception.ErrorException;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class MovimientoDto {
    private Long movimientoId;

    private LocalDate fechaMovimiento;

    private String tipoMovimiento;

    private BigDecimal valor;

    private BigDecimal saldo;

    private Cuenta cuenta;

    public MovimientoDto asignarMovimientoId(Long movimientoId) {
        this.movimientoId = movimientoId;
        return this;
    }

    public MovimientoDto asignarFechaMovimiento(LocalDate fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
        return this;
    }

    public MovimientoDto asignarTipoMovimiento() {
        this.tipoMovimiento = this.valor.compareTo(BigDecimal.ZERO) < 0 ? "Retiro" : "Deposito";
        return this;
    }

    public MovimientoDto asignarValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    public MovimientoDto asignarSaldo(BigDecimal saldo) throws ErrorException {
        if (saldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new ErrorException("El valor a debitar es mayor que el saldo disponible");
        }
        this.saldo = saldo;
        return this;
    }

    public MovimientoDto asignarCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
        return this;
    }

    public Movimiento build() {
        return new Movimiento(this);
    }

}

