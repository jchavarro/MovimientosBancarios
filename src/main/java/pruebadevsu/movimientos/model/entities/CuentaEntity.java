package pruebadevsu.movimientos.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entidad de cuenta.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cuenta")
public class CuentaEntity {
    /**
     * Identificador de la cuenta bancaria.
     */
    @Id
    @Column(name = "numero_cuenta")
    private Integer numeroCuenta;

    /**
     * Tipo de la cuenta.
     * Tipos: Ahorros y Corriente.
     */
    @Column(name = "tipo_cuenta")
    private String tipoCuenta;

    /**
     * Saldo inicial de la cuenta en dolares.
     */
    @Column(name = "saldo_inicial")
    private Double saldoInicial;

    /**
     * Estados de la cuenta.
     * Habilitada = true
     * Deshabilitada = false
     */
    private Boolean estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private ClienteEntity clienteEntity;

}
