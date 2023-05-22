package pruebadevsu.movimientos.service.implementations;

import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import pruebadevsu.movimientos.exceptions.types.BadRequestException;
import pruebadevsu.movimientos.exceptions.types.NotFoundException;
import pruebadevsu.movimientos.model.entities.ClienteEntity;
import pruebadevsu.movimientos.model.entities.PersonaEntity;
import pruebadevsu.movimientos.model.repositories.ClienteRepository;
import pruebadevsu.movimientos.service.utils.ClienteFactory;
import pruebadevsu.movimientos.web.dto.ClienteDto;

import java.util.Optional;

/**
 * Clase de test para el servicio de cliente.
 *
 * @author Juan Chavarro
 */
@ExtendWith(MockitoExtension.class)
class ClienteServiceImplTest {

    /**
     * Repositorio del cliente.
     */
    @Mock
    private ClienteRepository clienteRepositorio;

    /**
     * Mapeador de Objetos.
     */
    @Mock
    private ModelMapper modelMapper;

    /**
     * Implementacion del servicio instanciado con sus dependencias.
     */
    @InjectMocks
    private ClienteServiceImpl clienteService;

    /**
     * instanciacion de los mocks.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @DisplayName("Obtener cliente por Id de forma exitosa")
    @Test
    void obtenerClientePorId() {
        Integer clienteId = 1;
        ClienteDto clienteEsperado = new ClienteDto(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteEntity clienteEntity = new ClienteEntity(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        Mockito.when(clienteRepositorio.findById(1))
                .thenReturn(java.util.Optional.of(new ClienteEntity(1, "Juan", "hombre", 24,
                        "123456789", "casa 1", "3245612", "1234", true)));

        Mockito.when(modelMapper.map(clienteEntity, ClienteDto.class)).thenReturn(clienteEsperado);

        ClienteServiceImpl clienteService = new ClienteServiceImpl(modelMapper, clienteRepositorio);
        ClienteDto clienteResultado = clienteService.obtenerClientePorId(clienteId);

        Assertions.assertEquals(clienteEsperado, clienteResultado);

        Mockito.verify(clienteRepositorio).findById(clienteId);
    }

    @DisplayName("Obtener cliente por Id de forma fallida")
    @Test
    void obtenerClientePorIdExcepcion() {
        Integer clienteId = 1;
        Mockito.when(clienteRepositorio.findById(clienteId)).thenReturn(Optional.empty());

        ClienteServiceImpl clienteService = new ClienteServiceImpl(modelMapper, clienteRepositorio);
        Assertions.assertThrows(NotFoundException.class, () -> {
            clienteService.obtenerClientePorId(clienteId);
        });

        Mockito.verify(clienteRepositorio).findById(clienteId);
    }

    @DisplayName("Crear cliente de forma exitosa")
    @Test
    void crearCliente() {
        ClienteDto clienteDtoInicial = new ClienteDto(null, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteEntity clienteEntity = new ClienteEntity(null, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteEntity clienteEntityEsperado = new ClienteEntity(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);

        Mockito.when(modelMapper.map(clienteDtoInicial, ClienteEntity.class)).thenReturn(clienteEntity);
        Mockito.when(clienteRepositorio.save(clienteEntity)).thenReturn(clienteEntityEsperado);
        Mockito.when(modelMapper.map(clienteEntity, ClienteDto.class)).thenReturn(clienteDtoEsperado);

        ClienteServiceImpl clienteService = new ClienteServiceImpl(modelMapper, clienteRepositorio);
        ClienteDto clienteResultado = clienteService.crearCliente(clienteDtoInicial);

        Assertions.assertEquals(clienteDtoEsperado, clienteResultado);

        Mockito.verify(clienteRepositorio).save(clienteEntity);
    }

    @DisplayName("Actualizar cliente de forma exitosa")
    @Test
    void actualizarCliente() {
        ClienteDto clienteDtoInicial = new ClienteDto(1, "Camilo", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteEntity clienteEntity = new ClienteEntity(1, "Camilo", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Camilo", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);

        Mockito.when(clienteRepositorio.findById(clienteDtoInicial.getPersonaId()))
                .thenReturn(Optional.of(clienteEntity));
        Mockito.when(modelMapper.map(clienteDtoInicial, ClienteEntity.class))
                .thenReturn(clienteEntity);
        Mockito.when(clienteRepositorio.save(clienteEntity))
                .thenReturn(clienteEntity);
        Mockito.when(modelMapper.map(clienteEntity, ClienteDto.class))
                .thenReturn(clienteDtoEsperado);

        ClienteDto clienteDtoActualizado = clienteService.actualizarCliente(clienteDtoInicial);
        Assertions.assertEquals(clienteDtoInicial, clienteDtoActualizado);

        Mockito.verify(clienteRepositorio).findById(clienteDtoInicial.getPersonaId());
        Mockito.verify(clienteRepositorio).save(clienteEntity);

    }

    @DisplayName("Eliminar cliente de forma exitosa")
    @Test
    void eliminarCliente() {
        Integer clienteId = 1;
        PersonaEntity clienteEntity = new PersonaEntity();
        clienteEntity.setPersonaId(clienteId);

        Mockito.when(clienteRepositorio.findById(clienteId))
                .thenReturn(Optional.of(clienteEntity));

        Mockito.doNothing().when(clienteRepositorio).deleteById(clienteId);

        Boolean resultado = clienteService.eliminarCliente(clienteId);

        Assertions.assertTrue(resultado);

        Mockito.verify(clienteRepositorio).findById(clienteId);
        Mockito.verify(clienteRepositorio).deleteById(clienteId);
    }

}