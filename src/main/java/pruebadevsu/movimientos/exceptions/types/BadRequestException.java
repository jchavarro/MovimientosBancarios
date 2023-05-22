package pruebadevsu.movimientos.exceptions.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Clase manejo de excepcion bad request.
 *
 * @author Juan Chavarro
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    /**
     * Excepcion bad request.
     * @param message mensaje modificado de error.
     */
    public BadRequestException(String message) {
        super(message);
    }
}