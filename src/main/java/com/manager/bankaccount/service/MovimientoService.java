package com.manager.bankaccount.service;

import com.manager.bankaccount.dto.ReporteMovimientoDto;
import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.entity.Movimiento;
import com.manager.bankaccount.exception.ErrorException;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoService {
    Movimiento getMovimientoById(Long idMovimiento);

    Movimiento guardarMovimiento(Movimiento movimiento) throws ErrorException;

    void eliminarMovimiento(Movimiento movimiento);

    void eliminarMovimiento(Long movimientoId);

    BigDecimal getTotalRetiroDiario(String tipoMovimiento, Cuenta cuenta);

    List<ReporteMovimientoDto> getEstadoCuentaByFechasAndCliente(LocalDate fechaDesde, LocalDate fechaHasta,
                                                                 String identificacion);

}
