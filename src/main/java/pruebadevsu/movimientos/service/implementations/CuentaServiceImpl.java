package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.BadRequestException;
import pruebadevsu.movimientos.exceptions.NotFoundException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.repositories.CuentaRepository;
import pruebadevsu.movimientos.service.interfaces.ClienteService;
import pruebadevsu.movimientos.service.interfaces.CuentaService;
import pruebadevsu.movimientos.service.utils.CuentaFactory;
import pruebadevsu.movimientos.web.dto.ClienteDto;
import pruebadevsu.movimientos.web.dto.CuentaDto;
import pruebadevsu.movimientos.web.dto.reponse.CuentaResponseDto;

import javax.swing.text.html.parser.Entity;

/**
 * Servicio para la cuenta.
 *
 * @author Juan Chavarro
 */
@Service
@Slf4j
public class CuentaServiceImpl implements CuentaService {

    /**
     * Permite la conversión de un objeto a otro.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Repositorio de cuenta.
     */
    @Autowired
    private CuentaRepository cuentaRepositorio;

    /**
     * Servicio del cliente.
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Metodo get para obtener cuenta por su identificacion.
     *
     * @param numeroCuenta identificacion de la cuenta.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public CuentaResponseDto obtenerCuentaPorId(final Integer numeroCuenta) {
        log.info("Consulta de cuenta : " + numeroCuenta);
        CuentaEntity cuentaEntity = cuentaRepositorio.findById(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la cuenta."));
        return modelMapper.map(cuentaEntity, CuentaResponseDto.class);
    }

    /**
     * Metodo post para crear cuenta a partir del objeto cuenta.
     *
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public CuentaResponseDto crearCuenta(final CuentaDto cuentaDto) {
        log.info("Creacion de cuenta " + cuentaDto.getNumeroCuenta());
        if (validarCuenta(cuentaDto)) {
            return modelMapper.map(cuentaRepositorio
                            .save(CuentaFactory.crearCuentaClienteEntity(cuentaDto,
                                    modelMapper.map(clienteService.obtenerClientePorId(cuentaDto.getClienteId()), ClienteEntity.class))),
                    CuentaResponseDto.class);
        } else {
            throw new BadRequestException
                    ("El numero de cuenta, el tipo de cuenta, el cliente y el estado del cliente no pueden ser vacios");
        }
    }

    /**
     * Metodo de actualizar la información completa del cuenta.
     *
     * @param cuentaDto Objeto cuenta con su informacion.
     * @return Objeto de transferencia de datos de la cuenta.
     */
    @Override
    public CuentaDto actualizarCuenta(final CuentaDto cuentaDto) {
        return null;
    }

    /**
     * Metodo de eliminar cuenta por su id.
     *
     * @param numeroCuenta Identifiacion de la cuenta.
     * @return True si es eliminada.
     */
    @Override
    public Boolean eliminarCuenta(final Integer numeroCuenta) {
        return null;
    }

    /**
     * Validacion de campos importantes para el registro y actualización de la cuenta.
     *
     * @param cuentaDto informacion del cliente.
     * @return true si todos los campos estan llenos.
     */
    private boolean validarCuenta(final CuentaDto cuentaDto) {
        return (cuentaDto.getEstado() != null ||
                !cuentaDto.getTipoCuenta().isEmpty() ||
                cuentaDto.getClienteId() != null ||
                cuentaDto.getNumeroCuenta() != null);
    }
}
