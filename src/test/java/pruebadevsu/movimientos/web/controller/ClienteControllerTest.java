package pruebadevsu.movimientos.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pruebadevsu.movimientos.service.implementations.ClienteServiceImpl;
import pruebadevsu.movimientos.web.dto.ClienteDto;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    /**
     * mock para probar endpoints.
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * instancia de mapeador de Json a objetos.
     */
    ObjectMapper objectMapper = new ObjectMapper();;

    /**
     * Mock para el serivicio de cliente.
     */
    @MockBean
    private ClienteServiceImpl clienteServiceImpl;

    @Test
    void obtenerCliente() throws Exception {
        int clienteId = 1;
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);

        Mockito.when(clienteServiceImpl.obtenerClientePorId(clienteId)).thenReturn(clienteDtoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.get("/clientes")
                        .param("clienteId", String.valueOf(clienteId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDtoEsperado)));

        Mockito.verify(clienteServiceImpl).obtenerClientePorId(clienteId);
    }

    @Test
    void crearCliente() throws Exception {
        ClienteDto clienteDtoInicial = new ClienteDto(null, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);

        Mockito.when(clienteServiceImpl.crearCliente(clienteDtoInicial)).thenReturn(clienteDtoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.post("/clientes")
                        .content(objectMapper.writeValueAsString(clienteDtoInicial))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDtoEsperado)));

        Mockito.verify(clienteServiceImpl).crearCliente(clienteDtoInicial);
    }

    @Test
    void actualizarCliente() throws Exception {
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Camilo", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", true);

        Mockito.when(clienteServiceImpl.actualizarCliente(Mockito.any(ClienteDto.class))).thenReturn(clienteDtoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.put("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDtoEsperado)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDtoEsperado)));

        Mockito.verify(clienteServiceImpl).actualizarCliente(Mockito.any(ClienteDto.class));
    }

    @Test
    void eliminarCliente() throws Exception {
        int clienteId = 1;
        boolean eliminadoEsperado = true;

        Mockito.when(clienteServiceImpl.eliminarCliente(clienteId)).thenReturn(eliminadoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.delete("/clientes")
                        .param("clienteId", String.valueOf(clienteId)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(String.valueOf(eliminadoEsperado)));

        Mockito.verify(clienteServiceImpl).eliminarCliente(clienteId);
    }

    @Test
    void editarEstadoCliente() throws Exception {
        int clienteId = 1;
        boolean estadoCliente = false;
        ClienteDto clienteDtoEsperado = new ClienteDto(1, "Juan", "hombre", 24,
                "123456789", "casa 1", "3245612", "1234", false);

        Mockito.when(clienteServiceImpl.editarEstadoCliente(clienteId, estadoCliente)).thenReturn(clienteDtoEsperado);

        mockMvc.perform(MockMvcRequestBuilders.patch("/clientes/estado")
                        .param("clienteId", String.valueOf(clienteId))
                        .param("estadoCliente", String.valueOf(estadoCliente))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(clienteDtoEsperado)));

        Mockito.verify(clienteServiceImpl).editarEstadoCliente(clienteId, estadoCliente);
    }
}