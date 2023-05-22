package pruebadevsu.movimientos.model.entities;

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

    public ClienteEntity(Integer personaId, String nombre, String genero, Integer edad, String identificacion,
                         String direccion, String telefono, String password, Boolean estado) {
        super(personaId, nombre, genero, edad, identificacion, direccion, telefono);
        this.password = password;
        this.estado = estado;
    }

    public ClienteEntity(String password, Boolean estado) {
        this.password = password;
        this.estado = estado;
    }
}
