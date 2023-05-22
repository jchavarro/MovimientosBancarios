package pruebadevsu.movimientos.exceptions.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase manejo de excepcion unprocessable.
 *
 * @author Juan Chavarro
 */
@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException {
    /**
     * Excepcion Unprocessable entity.
     * @param message mensaje modificado de error.
     */
    public UnprocessableEntityException(String message) {
        super(message);
    }
}
