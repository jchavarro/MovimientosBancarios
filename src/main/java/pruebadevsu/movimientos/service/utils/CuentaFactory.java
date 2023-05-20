package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.web.dto.ClienteDto;
import pruebadevsu.movimientos.web.dto.CuentaDto;
import pruebadevsu.movimientos.web.dto.reponse.CuentaResponseDto;

/**
 * clase factory para el cliente.
 */
public final class CuentaFactory {
    /**
     * constructor vacio.
     */
    private CuentaFactory() {
    }

    /**
     * Creacion de cuenta a partir de la informacion de la cuenta y el cliente.
     * @param cuentaDto
     * @param clienteEntity
     * @return
     */
    public static CuentaEntity crearCuentaClienteEntity(final CuentaDto cuentaDto, final ClienteEntity clienteEntity) {
        return CuentaEntity.builder()
                .numeroCuenta(cuentaDto.getNumeroCuenta())
                .tipoCuenta(cuentaDto.getTipoCuenta())
                .saldoInicial(cuentaDto.getSaldoInicial())
                .estado(cuentaDto.getEstado())
                .clienteEntity(clienteEntity)
                .build();
    }
}
