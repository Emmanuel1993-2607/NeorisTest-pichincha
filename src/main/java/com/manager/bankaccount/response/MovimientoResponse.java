package com.manager.bankaccount.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MovimientoResponse {
    private Long movimientoId;

    private BigDecimal valor;

    private String numeroCuenta;

}
