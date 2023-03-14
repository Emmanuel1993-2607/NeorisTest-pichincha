package com.manager.bankaccount.Controller;

import com.manager.bankaccount.dto.MovimientoDto;
import com.manager.bankaccount.dto.ReporteMovimientoDto;
import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.entity.Movimiento;
import com.manager.bankaccount.exception.ErrorException;
import com.manager.bankaccount.response.MovimientoResponse;
import com.manager.bankaccount.service.CuentaService;
import com.manager.bankaccount.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoControl {

    @Autowired
    private MovimientoService movimientoService;
    @Autowired
    private CuentaService cuentaService;

    @PostMapping("/save")
    public ResponseEntity<String> guardarMovimiento(@RequestBody MovimientoResponse movimientoResponse) {
        Cuenta cuenta = this.cuentaService.getCuentaByNumeroCuenta(movimientoResponse.getNumeroCuenta());
        if (cuenta == null) {
            return new ResponseEntity<String>("No existe la cuenta numero: " + movimientoResponse.getNumeroCuenta(),
                    HttpStatus.OK);
        } else {
            Movimiento movimiento;
            try {
                movimiento = new MovimientoDto().asignarCuenta(cuenta).asignarFechaMovimiento(LocalDate.now())
                        .asignarValor(movimientoResponse.getValor())
                        .asignarSaldo(cuenta.getSaldo().add(movimientoResponse.getValor())).asignarTipoMovimiento().build();
            } catch (ErrorException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
            }

            try {
                Movimiento movimientoGuardado = this.movimientoService.guardarMovimiento(movimiento);
                cuenta.setSaldo(movimientoGuardado.getSaldo());
                this.cuentaService.guardarCuenta(cuenta);
                if (movimientoGuardado.getMovimientoId() == null) {
                    return new ResponseEntity<String>("Registro no guardado", HttpStatus.OK);
                } else {
                    return new ResponseEntity<String>("Movimiento guardado correctamente. Codigo de movimiento: "
                            + movimientoGuardado.getMovimientoId(), HttpStatus.OK);
                }
            } catch (ErrorException e) {
                return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
            }

        }
    }

    @GetMapping("/reportes/{identificacion}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<List<Object>> findClienteById(@PathVariable String identificacion,
                                                        @PathVariable String fechaDesde, @PathVariable String fechaHasta) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<ReporteMovimientoDto> reporte = this.movimientoService.getEstadoCuentaByFechasAndCliente(
                LocalDate.parse(fechaDesde, formatter), LocalDate.parse(fechaHasta, formatter), identificacion);
        if (reporte != null) {
            return new ResponseEntity(reporte, HttpStatus.OK);
        }

        List<ReporteMovimientoDto> resp = new ArrayList<>();
        return new ResponseEntity(resp, HttpStatus.OK);
    }
}
