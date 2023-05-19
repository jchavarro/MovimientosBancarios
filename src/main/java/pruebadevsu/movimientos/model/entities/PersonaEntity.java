package pruebadevsu.movimientos.model.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Entidad del cliente.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class PersonaEntity {

    /**
     * Identificador de la clase cliente.
     */
    @Id
    @Column(name = "persona_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer personaId;
    /**
     * Nombre de la persona.
     */
    private String nombre;

    /**
     * Genero de la persona.
     */
    protected String genero;

    /**
     * Edad en años de la persona.
     */
    protected Integer edad;

    /**
     * Identificacion independiente del tipo de documento, de la persona.
     */
    protected String identificacion;

    /**
     * Direccion de residencia de la persona.
     */
    protected String direccion;

    /**
     * Telefono de la persona.
     */
    protected String telefono;
}
