package com.manager.bankaccount.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import com.manager.bankaccount.dto.ReporteMovimientoDto;
import com.manager.bankaccount.entity.Cuenta;
import com.manager.bankaccount.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    Optional<Movimiento> findByMovimientoId(Long movimientoId);

    @Query(value = "SELECT * FROM Movimiento m "
            + " WHERE m.fechaMovimiento = current_date AND m.tipoMovimiento = :tipoMovimiento"
                    + " AND m.cuenta = :cuenta ", nativeQuery = true)
    Optional<List<Movimiento>> getAllByFechaMovimientoAndTipoMovimientoAndCuenta(
            @Param("tipoMovimiento") String tipoMovimiento, @Param("cuenta") Cuenta cuenta);

    @Query(value = "SELECT * FROM Movimiento movi "
            + " WHERE movi.fechaMovimiento BETWEEN :fechaDesde AND :fechaHasta "
            + " AND movi.cuenta.cliente.clienteId = :clienteId ", nativeQuery = true)
    Optional<List<ReporteMovimientoDto>> getEstadoCuentaByFechaAndCliente(@Param("fechaDesde") LocalDate fechaDesde,
                                                                          @Param("fechaHasta") LocalDate fechaHasta, @Param("clienteId") Long clienteId);

    Movimiento findTopByOrderByIdMovimientoDesc();
}
