package com.manager.bankaccount.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "CLIENTE")
public class Cliente extends Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id", nullable = false, unique = true)
    private Long clienteId;

    @Column(name = "contrasenia")
    private String contrasenia;

    @Column(name = "estado")
    private String estado;

    public void setId(Long idCliente) {
    }
}

