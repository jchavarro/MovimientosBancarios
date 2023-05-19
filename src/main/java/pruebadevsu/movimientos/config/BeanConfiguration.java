package pruebadevsu.movimientos.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de inyeccion de dependencias.
 *
 * @author Juan Chavarro
 */
@Configuration
public class BeanConfiguration {

    /**
     * Inyeccion de dependencias del mapeador de objetos.
     * @return instancia modelmapper.
     */
    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
