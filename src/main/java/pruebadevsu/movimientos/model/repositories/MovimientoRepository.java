package pruebadevsu.movimientos.model.repositories;

import org.springframework.data.repository.CrudRepository;
import pruebadevsu.movimientos.model.entities.MovimientoEntity;

import java.util.Date;
import java.util.List;

public interface MovimientoRepository extends CrudRepository<MovimientoEntity, Integer> {

    List<MovimientoEntity> findAllByFechaBetween(final Date fechaInicio, final Date fechaFin);

    List<MovimientoEntity> findAll();
}
