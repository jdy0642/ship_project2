package com.ship.web.futsal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FutsalRepository extends CrudRepository<Futsal, Long>{

}
