package com.manager.bankaccount.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class Persona {

	@Column (name = "nombre")
	private String nombre;

	@Column
	private String genero;

	@Column
	private Integer edad;

	@Column
	private String identificacion;

	@Column
	private String direccion;

	@Column
	private String telefono;

}

