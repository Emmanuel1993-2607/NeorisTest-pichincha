package com.manager.bankaccount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manager.bankaccount.dto.MovimientoDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "MOVIMIENTO")
@NoArgsConstructor
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name =  "MOVIMIENTO_ID", nullable = false, unique = true)
    private Long movimientoId;

    @Column(name = "FECHA_MOVIMIENTO")
    private LocalDate fechaMovimiento;

    @Column(name = "TIPO_MOVIMIENTO")
    private String tipoMovimiento;

    @Column(name = "VALOR")
    private BigDecimal valor;

    @Column(name = "SALDO")
    private BigDecimal saldo;

    @JoinColumn(name = "cuenta_id", nullable = false)
    @ManyToOne
    private Cuenta cuenta;


    public Movimiento(MovimientoDto builder) {
        this.movimientoId = builder.getMovimientoId();
        this.fechaMovimiento = builder.getFechaMovimiento();
        this.tipoMovimiento = builder.getTipoMovimiento();
        this.valor = builder.getValor();
        this.saldo = builder.getSaldo();
        this.cuenta = builder.getCuenta();
    }

    public Movimiento(BigDecimal valor) {
        super();
        this.valor = valor;
    }
}
