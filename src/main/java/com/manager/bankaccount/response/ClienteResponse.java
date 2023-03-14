package com.manager.bankaccount.response;

import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.enumeration.ConsultaEnumeration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    private Cliente cliente;

    private ConsultaEnumeration estadoConsultaEnum;

    private String mensaje;

    public ClienteResponse(ConsultaEnumeration estadoConsultaEnum, String mensaje) {
        super();
        this.estadoConsultaEnum = estadoConsultaEnum;
        this.mensaje = mensaje;
    }
}
