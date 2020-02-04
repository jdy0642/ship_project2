package com.ship.web.reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = "http://localhost:8081")
public class ReservationController {
	@Autowired private ReservationRepository reservationrepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@GetMapping("/1")
	public List<Reservation> reslist(){
		p.accept("res 컨트롤러 들어옴");
		Iterable<Reservation> res = reservationrepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		return list1.stream().collect(Collectors.toList());
	}
	
	@GetMapping
	public List<Reservation> filterList(){
		Iterable<Reservation> res = reservationrepository.findAll();
		List<Reservation> list2 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list2.add(dto1);
		}
		return list2.stream().collect(Collectors.toList());
	}
	
}