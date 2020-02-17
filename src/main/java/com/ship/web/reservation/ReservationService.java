package com.ship.web.reservation;

import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	@Autowired private ReservationRepository reservationRepository;
	
	public Iterable<Map<String, Object>> reservationTable(){
		return reservationRepository.findReservationTable().stream()
				.collect(Collectors.toList());
	}
}
