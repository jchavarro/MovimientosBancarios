package pruebadevsu.movimientos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Objeto de transferencia de datos de movimiento.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoDto {

    /**
     * Llave primaria de la tabla.
     */
    private Integer movimientosId;

    /**
     * Fecha del movimiento.
     */
    private Date fecha;

    /**
     * Tipo del movimiento.
     * Tipos: Retiro, deposito.
     */
    private String tipoMovimiento;

    /**
     * Valor en dolares del movimiento.
     */
    private Double valor;

    /**
     * Saldo en dolares despues del movimiento.
     */
    private Double saldo;

    /**
     * Id de cuenta correspondiente.
     */
    private Integer numeroCuenta;
}
