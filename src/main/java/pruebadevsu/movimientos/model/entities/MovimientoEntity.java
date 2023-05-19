package pruebadevsu.movimientos.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * Entidad movimientos.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimientos")
public class MovimientoEntity {

    /**
     * Llave primaria de la tabla.
     */
    @Id
    @Column(name = "id_movimiento")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer movimientoId;

    /**
     * Fecha del movimiento.
     */
    private Date fecha;

    /**
     * Tipo del movimiento.
     * Tipos: Ahorros y Corriente.
     */
    @Column(name = "tipo_movimiento")
    private String tipoMovimiento;

    /**
     * Valor en dolares del movimiento.
     */
    private Double valor;

    /**
     * Saldo en dolares despues del movimiento.
     */
    private Double saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    private CuentaEntity cuentaEntity;
}
