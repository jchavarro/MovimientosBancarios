package pruebadevsu.movimientos.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto de transferencia de datos de cuenta.
 *
 * @author Juan Chavarro
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuentaDto {

    /**
     * Identificador de la cuenta bancaria.
     */
    private Integer numeroCuenta;

    /**
     * Tipo de la cuenta.
     * Tipos: Ahorros y Corriente.
     */
    private String tipoCuenta;

    /**
     * Saldo inicial de la cuenta en dolares.
     */
    private Double saldoInicial;

    /**
     * Estados de la cuenta.
     * Habilitada = true
     * Deshabilitada = false
     */
    private Boolean estado;

    /**
     * Id de cliente correspondiente.
     */
    private Integer clienteId;
}
