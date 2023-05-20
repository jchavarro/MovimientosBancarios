package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.BadRequestException;
import pruebadevsu.movimientos.exceptions.NotFoundException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.CuentaEntity;
import pruebadevsu.movimientos.model.entities.PersonaEntity;
import pruebadevsu.movimientos.model.repositories.CuentaRepository;
import pruebadevsu.movimientos.service.interfaces.ClienteService;
import pruebadevsu.movimientos.service.interfaces.CuentaService;
import pruebadevsu.movimientos.service.interfaces.adapter.ClienteServiceAdapter;
import pruebadevsu.movimientos.service.utils.ClienteFactory;
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
    private ClienteServiceAdapter clienteServiceAdapter;

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
                .orElseThrow(() -> new NotFoundException("No se encontró la cuenta: " + numeroCuenta));
        return CuentaFactory.crearCuentaNomreClienteDto(cuentaEntity, cuentaEntity.getClienteEntity().getNombre());
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
            ClienteEntity clienteEntity = clienteServiceAdapter.obtenerClienteEntityPorId(cuentaDto.getClienteId());
            return CuentaFactory.crearCuentaNomreClienteDto(cuentaRepositorio.save(
                            CuentaFactory.crearCuentaClienteEntity(cuentaDto, clienteEntity)),
                    clienteEntity.getNombre());
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
    public CuentaResponseDto actualizarCuenta(final CuentaDto cuentaDto) {
        log.info("Actualizacion de cuenta : " + cuentaDto.getNumeroCuenta());
        cuentaRepositorio.findById(cuentaDto.getNumeroCuenta())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la cuenta"));
        ClienteEntity clienteEntity = clienteServiceAdapter.obtenerClienteEntityPorId(cuentaDto.getClienteId());
        return CuentaFactory.crearCuentaNomreClienteDto(cuentaRepositorio.save(
                        CuentaFactory.crearCuentaClienteEntity(cuentaDto, clienteEntity)),
                clienteEntity.getNombre());
    }

    /**
     * Metodo de eliminar cuenta por su id.
     *
     * @param numeroCuenta Identifiacion de la cuenta.
     * @return True si es eliminada.
     */
    @Override
    public Boolean eliminarCuenta(final Integer numeroCuenta) {
        log.info("Eliminacion de cuenta : " + numeroCuenta);
        CuentaEntity cuentaEntity = cuentaRepositorio.findById(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la cuenta: " + numeroCuenta));
        cuentaRepositorio.deleteById(cuentaEntity.getNumeroCuenta());
        return Boolean.TRUE;
    }

    /**
     * Metodo de editar el estado de la cuenta por su numero de cuenta.
     *
     * @param numeroCuenta numero de la cuenta.
     * @param estadoCuenta estado nuevo de la cuenta.
     * @return Objeto de transferencia de datos editados de la cuenta.
     */
    @Override
    public CuentaResponseDto editarEstadoCliente(Integer numeroCuenta, Boolean estadoCuenta) {
        log.info("Edicion del estado de cuenta : " + numeroCuenta);
        CuentaEntity cuentaEntity = cuentaRepositorio.findById(numeroCuenta)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado la cuenta: " + numeroCuenta));
        return CuentaFactory.crearCuentaNomreClienteDto(cuentaRepositorio
                        .save(CuentaFactory.editarEstadoCuenta(cuentaEntity, estadoCuenta)),
                cuentaEntity.getClienteEntity().getNombre());
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
