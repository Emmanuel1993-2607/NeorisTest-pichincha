package com.manager.bankaccount.response;


import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class CuentaResponse {
    private Long cuentaId;

    private String numeroCuenta;

    private String tipoCuenta;



    private BigDecimal saldoInicial;

    private String estado;

    private static String identificacionCliente;


    public static String getIdentificacionCliente() {
        return identificacionCliente;
    }

    public void setIdentificacionCliente(String s) {
    }

}
