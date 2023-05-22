package pruebadevsu.movimientos.exceptions.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase manejo de excepcion not found.
 *
 * @author Juan Chavarro
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    /**
     * Excepcion not found.
     * @param message mensaje modificado de error.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
