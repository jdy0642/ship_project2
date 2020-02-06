package com.ship.web.reservation;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.type.BigIntegerType;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.futsal.FutsalMatch;
import com.ship.web.futsal.FutsalMatchRepository;
import com.ship.web.lol.Lol;
import com.ship.web.person.Person;
import com.ship.web.person.PersonRepository;
import com.ship.web.util.Constants;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = Constants.LOCAL)
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private FutsalMatchRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@Autowired Reservation reservation;
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
	      SimpleDateFormat sdf = new SimpleDateFormat("d");
          System.out.println("오늘날짜 ====>>>>>"+sdf.format(new Date())); // test commit!2

	      return list2.stream()
	    		  .sorted(Comparator.comparing(Reservation::getResseq).reversed())
	    		  .filter(t-> sdf.format(new Date(t.getResdate())).equals(sdf.format(new Date())) ) //&& t.getResdate() < new Date().getTime()
	    		  .collect(Collectors.toList());
	   }

	
	@PostMapping("/{matchId}")
	public boolean createReservation(@PathVariable Long matchId, @RequestBody Person person) {
		reservation.setPersonseq(person);
		reservation.setFutsalmatchseq(futsalMatchRepository.findById(matchId).get());
		reservation.setResdate(System.currentTimeMillis());
		reservationRepository.save(reservation);
		return reservationRepository.findByResdate(reservation.getResdate()) != null;
	}
	
}