package com.ship.web.reservation;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ship.web.person.Person;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {

	public Reservation findByResdate(Long resdate);
	@Query(value = "SELECT r.resseq, r.resdate, r.km, r.score, r.win, p.userid, p.name, f.stadiumname\n" + 
			"FROM reservation r JOIN person p\n" + 
			"ON r.personseq = p.personseq\n" + 
			"JOIN futsal f\n" + 
			"ON f.futsalseq = r.futsalseq", nativeQuery = true) 
	public List<Map<String, Object>> findReservationTable();
	public List<Reservation> findByPersonseq(Person personseq);
	
}
