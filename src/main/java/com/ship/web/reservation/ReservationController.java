package com.ship.web.reservation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.futsal.FutsalMatch;
import com.ship.web.futsal.FutsalMatchRepository;
import com.ship.web.person.Person;
import com.ship.web.person.PersonRepository;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = "http://localhost:8081")
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private PersonRepository personRepository;
	@Autowired private Reservation reservation;
	@Autowired private FutsalMatchRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@GetMapping("/1")
	public List<Reservation> reslist(){
		p.accept("res 컨트롤러 들어옴");
		Iterable<Reservation> res = reservationRepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		return list1.stream().collect(Collectors.toList());
	}

	 @GetMapping("/2")
	   public List<Reservation> filterList(){
	      Iterable<Reservation> res = reservationRepository.findAll();
	      List<Reservation> list2 = new ArrayList<>();
	      for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list2.add(dto1);
	      }
	      return list2.stream().collect(Collectors.toList());
	   }

	
	@PostMapping("/{matchId}")
	public boolean createReservation(@PathVariable Long matchId, @RequestBody Person person) {
		reservation.setPersonseq(person);
		reservation.setFutsalmatchseq(futsalMatchRepository.findById(matchId).get());
		reservation.setResdate(System.currentTimeMillis());
		reservationRepository.save(reservation);
		return reservationRepository.findByResdate(reservation.getResdate()) != null;
	}
	
	@PutMapping("/{resseq}")
	public boolean updateReservation(@PathVariable Long resseq, @RequestBody Reservation reservation) {
		Reservation res = reservationRepository.findById(resseq).get();
		res.setKm(reservation.getKm());
		res.setWin(reservation.getWin());
		res.setScore(reservation.getScore());
		reservationRepository.save(res);
		return reservationRepository
				.findByResdate(res.getResdate()).getKm() == reservation.getKm();
	}

}