package com.ship.web.reservation;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.futsal.FutsalMatch;
import com.ship.web.person.Person;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = "http://localhost:8081")
public class ReservationController {
	@Autowired ReservationRepository reservationrepository;
	@Autowired private Printer p;
	@Autowired ModelMapper modelMapper;
	
	@GetMapping("/1")
	public List<Reservation> reslist(){
		p.accept("res 컨트롤러 들어옴");
		Iterable<Reservation> res = reservationrepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		System.out.println("findAll");
		return list1.stream().collect(Collectors.toList());
	}
	
}