package pruebadevsu.movimientos.service.utils;

import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
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
     * @param cuentaDto Informacion de la cuenta.
     * @param clienteEntity informacion del cliente.
     * @return Cuenta con la informacion completa.
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

    /**
     * Creacion del response de cuenta con el nombre del cliente.
     * @param cuentaEntity Cuenta.
     * @param nombreCliente Nombre del cliente respectivo.
     * @return objeto response con el nombre de cliente
     */
    public static CuentaResponseDto crearCuentaNombreClienteDto(final CuentaEntity cuentaEntity,
                                                                final String nombreCliente) {
        return CuentaResponseDto.builder()
                .numeroCuenta(cuentaEntity.getNumeroCuenta())
                .tipoCuenta(cuentaEntity.getTipoCuenta())
                .saldoInicial(cuentaEntity.getSaldoInicial())
                .estado(cuentaEntity.getEstado())
                .nombreCliente(nombreCliente)
                .build();
    }

    /**
     * Cambiar estado a CuentaEntity.
     *
     * @param cuentaEntity entidad de cuenta a editar
     * @param estadoCuenta nuevo estado a editar
     * @return entidad de cuenta
     */
    public static CuentaEntity editarEstadoCuenta(final CuentaEntity cuentaEntity, final Boolean estadoCuenta) {
        cuentaEntity.setEstado(estadoCuenta);
        return cuentaEntity;
    }

    /**
     * Efectuar movimiento en cuenta.
     * @param cuentaEntity cuenta a realizar el movimiento.
     * @param valor valor del movimiento.
     * @return entidad de cuenta.
     */
    public static CuentaEntity efectuarMoviemientoEnCuenta(final CuentaEntity cuentaEntity, final Double valor) {
        cuentaEntity.setSaldoInicial(cuentaEntity.getSaldoInicial() + valor);
        return cuentaEntity;
    }
}
