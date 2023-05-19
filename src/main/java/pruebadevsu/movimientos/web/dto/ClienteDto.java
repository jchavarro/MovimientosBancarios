package pruebadevsu.movimientos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferencia de datos del cliente.
 *
 * @author Juan Chavarro
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClienteDto {

    /**
     * Identificacion del cliente
     */
    private Integer personaId;

    /**
     * Nombre de la persona.
     */
    private String nombre;

    /**
     * Genero de la persona.
     */
    private String genero;

    /**
     * Edad en años de la persona.
     */
    private Integer edad;

    /**
     * Identificacion independiente del tipo de documento, de la persona.
     */
    private String identificacion;

    /**
     * Direccion de residencia de la persona.
     */
    private String direccion;

    /**
     * Telefono de la persona.
     */
    private String telefono;

    /**
     * Contraseña del cliente.
     */
    private String password;

    /**
     * Estado del cliente.
     * Habilitado = true
     * Deshabilitado = false
     */
    private Boolean estado;

}
