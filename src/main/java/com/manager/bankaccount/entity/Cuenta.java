package com.manager.bankaccount.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.manager.bankaccount.dto.CuentaDto;
import lombok.Data;

@Data
@Entity
@Table(name = "CUENTA")
public class Cuenta {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCuenta")
    private Long idCuenta;

    @Column(name = "numeroCuenta", nullable = false)
    private String numeroCuenta;

    @Column(name = "tipoCuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldoInicial", nullable = false)
    private BigDecimal saldoInicial;

    @Column(name = "estado", nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clienteid", nullable = true)
    @JsonIgnore
    private Cliente cliente;

    public Cuenta(Long idCuenta, String numeroCuenta, String tipoCuenta, BigDecimal saldoInicial, String estado,
            Long idCliente) {
        Cliente cliente = new Cliente();
        cliente.setId(idCliente);
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.tipoCuenta = tipoCuenta;
        this.saldoInicial = saldoInicial;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Cuenta(CuentaDto cuentaDto) {
    }

    public Cuenta() {

    }


    public Long getCuentaId() {
        return null;
    }

    public <E> List getSaldo() {
        return null;
    }

    public void setSaldo(BigDecimal saldo) {

    }

    public void setCuentaId(long l) {

    }
}
