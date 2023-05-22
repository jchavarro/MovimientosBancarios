package pruebadevsu.movimientos.service.implementations;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebadevsu.movimientos.exceptions.types.BadRequestException;
import pruebadevsu.movimientos.exceptions.types.NotFoundException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.PersonaEntity;
import pruebadevsu.movimientos.model.repositories.ClienteRepository;
import pruebadevsu.movimientos.service.interfaces.ClienteService;
import pruebadevsu.movimientos.service.interfaces.adapter.ClienteServiceAdapter;
import pruebadevsu.movimientos.service.utils.ClienteFactory;
import pruebadevsu.movimientos.web.dto.ClienteDto;

/**
 * Servicio del cliente.
 *
 * @author Juan Chavarro
 */
@Service
@AllArgsConstructor
@Slf4j
public class ClienteServiceImpl implements ClienteService, ClienteServiceAdapter {

    /**
     * Permite la conversión de un objeto a otro que tenga atributos en común.
     */
    @Autowired
    private ModelMapper modelMapper;

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
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente: " + clienteId)),
                ClienteDto.class);
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
                .orElseThrow(() -> new NotFoundException("No se encontro el cliente."));
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
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente: " + clienteId));
        clienteRepositorio.deleteById(clienteEntidad.getPersonaId());
        return Boolean.TRUE;
    }

    /**
     * Edicion del estado de cliente por su id.
     * @param clienteId Identifiacion del cliente.
     * @param estadoCliente estado nuevo del cliente.
     * @return informacion del cliente editado.
     */
    @Override
    public ClienteDto editarEstadoCliente(Integer clienteId, Boolean estadoCliente) {
        log.info("Edicion del estado de cliente : " + clienteId);
        ClienteEntity clienteEntidad = (ClienteEntity) clienteRepositorio.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente."));
        return modelMapper.map(clienteRepositorio
                .save(ClienteFactory.editarEstadoCliente(clienteEntidad, estadoCliente)), ClienteDto.class);
    }

    /**
     * Obtiene cliente entity por id.
     * @param clienteId id del cliente.
     * @return Cliente entity.
     */
    @Override
    public ClienteEntity obtenerClienteEntityPorId(Integer clienteId) {
        log.info("Consulta de cliente entidad: " + clienteId);
        return (ClienteEntity) clienteRepositorio.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("No se ha encontrado el cliente."));
    }

    /**
     * Validacion de campos importantes para el registro y actualización del cliente.
     * @param clienteDto informacion del cliente.
     * @return true si todos los campos estan llenos.
     */
    public boolean validarCliente(final ClienteDto clienteDto) {
        return (!clienteDto.getIdentificacion().isEmpty() ||
                !clienteDto.getNombre().isEmpty() ||
                !clienteDto.getPassword().isEmpty());
    }
}
