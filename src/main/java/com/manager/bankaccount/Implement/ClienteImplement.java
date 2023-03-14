package com.manager.bankaccount.Implement;

import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.repository.ClienteRepository;
import com.manager.bankaccount.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteImplement implements ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Cliente getClienteById(Long idCliente) {
        return this.clienteRepository.findByClienteid(idCliente).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return this.clienteRepository.save(cliente);
    }

    @Override
    public void eliminarCliente(Cliente cliente) {
        this.clienteRepository.delete(cliente);
    }

    @Override
    public void eliminarCliente(Long idCliente) {
        Cliente cliente = this.getClienteById(idCliente);
        if (cliente != null) {
            this.eliminarCliente(cliente);
        }
    }

    @Override
    public Cliente getClienteByIdentificacion(String identificacion) {
        return this.clienteRepository.findByIdentificacion(identificacion).orElse(null);
    }
}
