package pruebadevsu.movimientos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Clase modificada de la respuesta de excepcion.
 *
 * @author Juan Chavarro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    /**
     * Hora y fecha de la excepcion.
     */
    private Date timestamp;

    /**
     * Mensaje de la excepcion.
     */
    private String mensaje;

    /**
     * Detalles de la excepcion.
     */
    private String detalles;

    /**
     * Codigo http de la excepcion.
     */
    private String httpCodeMessage;

}