package pruebadevsu.movimientos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Reporte de movimientos.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReporteDto {

    /**
     * Fecha del movimiento
     */
    private Date fecha;

    /**
     * Nombre del cliente
     */
    private String cliente;

    /**
     * Numero de la cuenta del movimiento.
     */
    private Integer numeroCuenta;

    /**
     * Tipo de cuenta
     */
    private String tipo;

    /**
     * Estado de la cuenta.
     */
    private Boolean estado;

    /**
     * Valor del movimiento.
     */
    private Double movimiento;

    /**
     * Saldo dispible despues del movimiento de la cuenta.
     */
    private Double saldoDisponible;
}
