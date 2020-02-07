package com.ship.web.reservation;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ship.web.person.Person;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public Reservation findByResdate(Long resdate);
	public List<Reservation> findByPersonseq(Person personseq);
}
