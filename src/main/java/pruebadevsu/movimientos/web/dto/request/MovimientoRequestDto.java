package pruebadevsu.movimientos.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferencia de datos de movimiento.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoRequestDto {

    /**
     * Valor en dolares del movimiento.
     */
    private Double valor;

    /**
     * Id de cuenta correspondiente.
     */
    private Integer numeroCuenta;
}
