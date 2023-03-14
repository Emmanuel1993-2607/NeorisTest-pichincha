package com.manager.bankaccount.Controller;

import com.manager.bankaccount.dto.CuentaDto;
import com.manager.bankaccount.entity.Cliente;
import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.response.CuentaResponse;
import com.manager.bankaccount.service.ClienteService;
import com.manager.bankaccount.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaControl {
    @Autowired
    private CuentaService cuentaService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<String> guardarCuenta(@RequestBody CuentaResponse cuentaResponse) {
        Cliente cliente = this.clienteService.getClienteByIdentificacion(CuentaResponse.getIdentificacionCliente());
        if (cliente == null) {
            return new ResponseEntity<String>(
                    "No existe el cliente con identificacion: " + cuentaResponse.getIdentificacionCliente(), HttpStatus.OK);
        } else {
            Cuenta cuenta = new CuentaDto().asignarCliente(cliente).asignarEstado(cuentaResponse.getEstado())
                    .asignarNumeroCuenta(cuentaResponse.getNumeroCuenta()).asignarSaldoInicial(cuentaResponse.getSaldoInicial())
                    .asignarTipoCuenta(cuentaResponse.getTipoCuenta()).asignarSaldo(cuentaResponse.getSaldoInicial()).build();

            Cuenta cuentaGuardada = this.cuentaService.guardarCuenta(cuenta);
            if (cuentaGuardada.getCuentaId() == null) {
                return new ResponseEntity<String>("Registro no guardado", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>(
                        "Cuenta guardada correctamente. Codigo de cuenta: " + cuentaGuardada.getCuentaId(),
                        HttpStatus.OK);
            }
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> actualizarCuenta(@RequestBody CuentaResponse cuentaTo) {
        Cuenta cuenta = this.cuentaService.getCuentaById(cuentaTo.getCuentaId());
        if (cuenta == null) {
            return new ResponseEntity<String>("Cuenta no encontrada con id: " + cuentaTo.getCuentaId(), HttpStatus.OK);
        } else {
            Cliente cliente = this.clienteService.getClienteByIdentificacion(cuentaTo.getIdentificacionCliente());
            Cuenta cuentaActualzar = new CuentaDto().asignarCliente(cliente)
                    .asignarEstado(cuentaTo.getEstado() == null ? cuenta.getEstado() : cuentaTo.getEstado())
                    .asignarNumeroCuenta(
                            cuentaTo.getNumeroCuenta() == null ? cuenta.getNumeroCuenta() : cuentaTo.getNumeroCuenta())
                    .asignarSaldoInicial(
                            cuentaTo.getSaldoInicial() == null ? cuenta.getSaldoInicial() : cuentaTo.getSaldoInicial())
                    .asignarTipoCuenta(
                            cuentaTo.getTipoCuenta() == null ? cuenta.getTipoCuenta() : cuentaTo.getTipoCuenta())
                    .asignarCuentaId(cuentaTo.getCuentaId()).build();
            this.cuentaService.guardarCuenta(cuentaActualzar);
            return new ResponseEntity<String>(
                    "Cuenta actualizada correctamente. Codigo de cuenta: " + cuentaActualzar.getCuentaId(),
                    HttpStatus.OK);
        }

    }

    @DeleteMapping("/delete/{idCuenta}")
    public ResponseEntity<String> deleteClienteById(@PathVariable Long idCuenta) {
        this.cuentaService.eliminarCuenta(idCuenta);
        return new ResponseEntity<String>("Registro eliminado correctamente", HttpStatus.OK);
    }
}
