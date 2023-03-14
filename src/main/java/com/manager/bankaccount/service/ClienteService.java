package com.manager.bankaccount.service;

import com.manager.bankaccount.entity.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteService {
    Cliente getClienteById(Long idCliente);

    Cliente guardarCliente(Cliente cliente);

    void eliminarCliente(Cliente cliente);

    void eliminarCliente(Long idCliente);

    Cliente getClienteByIdentificacion(String identificacion);
}
