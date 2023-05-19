package pruebadevsu.movimientos.service.implementations;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.BadRequestException;
import pruebadevsu.movimientos.exceptions.NotFoundException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.PersonaEntity;
import pruebadevsu.movimientos.model.repositories.ClienteRepository;
import pruebadevsu.movimientos.service.interfaces.ClienteService;
import pruebadevsu.movimientos.web.dto.ClienteDto;

/**
 * Servicio del cliente.
 *
 * @author Juan Chavarro
 */
@Service
@Slf4j
public class ClienteServiceImpl implements ClienteService {

    /**
     * Permite la conversión de un objeto a otro que tenga atributos en común.
     */
    private final ModelMapper modelMapper = new ModelMapper();

    /**
     * Repositorio de relación entre entidad de salud y usuario.
     */
    @Autowired
    private ClienteRepository clienteRepositorio;

    /**
     * Metodo get para obtener cliente por su identificacion.
     *
     * @param clienteId identificacion del cliente.
     * @return Objeto de transferencia de datos del cliente.
     */
    @Override
    public ClienteDto obtenerClientePorId(final Integer clienteId) {
        log.info("Consulta de cliente : " + clienteId);
        return modelMapper.map(clienteRepositorio.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente.")), ClienteDto.class);
    }

    /**
     * Metodo post para crear cliente a partir del objeto cliente.
     *
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    @Override
    public ClienteDto crearCliente(final ClienteDto clienteDto) {
        log.info("Creacion del cliente " + clienteDto.getNombre());
        if (validarCliente(clienteDto)) {
            return modelMapper.map(clienteRepositorio.save(modelMapper.map(clienteDto, ClienteEntity.class)),
                    ClienteDto.class);
        } else {
            throw new BadRequestException
                    ("La identificacion, el nombre, la contraseña y el estado del cliente no pueden ser vacios");
        }
    }

    /**
     * Metodo de actualizar la información completa del cliente.
     *
     * @param clienteDto Objeto cliente con su informacion.
     * @return Objeto de transferencia de datos del cliente.
     */
    @Override
    public ClienteDto actualizarCliente(final ClienteDto clienteDto) {
        log.info("Actualizacion de cliente : " + clienteDto.getNombre());
        clienteRepositorio.findById(clienteDto.getPersonaId())
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente."));
        return modelMapper.map(clienteRepositorio
                .save(modelMapper.map(clienteDto, ClienteEntity.class)), ClienteDto.class);
    }

    /**
     * Metodo de eliminar cliente por su id.
     *
     * @param clienteId Identifiacion del cliente.
     * @return True si es eliminado.
     */
    @Override
    public Boolean eliminarCliente(final Integer clienteId) {
        log.info("Eliminacion de cliente : " + clienteId);
        PersonaEntity clienteEntidad = clienteRepositorio.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente."));
        clienteRepositorio.deleteById(clienteEntidad.getPersonaId());
        return Boolean.TRUE;
    }

    private boolean validarCliente(final ClienteDto clienteDto) {
        return (!clienteDto.getIdentificacion().isEmpty() ||
                !clienteDto.getNombre().isEmpty() ||
                !clienteDto.getPassword().isEmpty());
    }
}
