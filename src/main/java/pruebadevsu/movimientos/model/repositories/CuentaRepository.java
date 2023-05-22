package pruebadevsu.movimientos.model.repositories;

import org.springframework.data.repository.CrudRepository;
import pruebadevsu.movimientos.model.entities.CuentaEntity;

import java.util.List;

public interface CuentaRepository extends CrudRepository<CuentaEntity, Integer> {

    List<CuentaEntity> findAll();
}
