package pruebadevsu.movimientos.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entidad del cliente.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cliente")
public class ClienteEntity extends PersonaEntity {


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
