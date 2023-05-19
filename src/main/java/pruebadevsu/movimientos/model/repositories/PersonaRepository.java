package pruebadevsu.movimientos.model.repositories;

import org.springframework.data.repository.CrudRepository;
import pruebadevsu.movimientos.model.entities.PersonaEntity;

public interface PersonaRepository extends CrudRepository<PersonaEntity, Integer> {
}
