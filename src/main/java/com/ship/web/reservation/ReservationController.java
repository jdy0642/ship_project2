package com.ship.web.reservation;
import java.math.BigInteger;
import com.ship.web.util.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.type.BigIntegerType;
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
import com.ship.web.lol.Lol;
import com.ship.web.person.Person;
import com.ship.web.person.PersonRepository;
import com.ship.web.proxy.Proxy;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = Constants.LOCAL)
//@CrossOrigin(origins = Constants.J_S3)
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private Reservation reservation;
	@Autowired private FutsalMatchRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@Autowired private Proxy pxy;
	@GetMapping("/1")
	public List<Reservation> reslist(){
		Iterable<Reservation> res = reservationRepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		System.out.println(list1.stream().map(Reservation::getResseq));
		return list1.stream().collect(Collectors.toList());
	}

	 @GetMapping("/onedaylist/{day}")
	   public List<Reservation> onedaylist(@PathVariable String day){
		 System.out.println("들어온 day : >>>>  "+day);
		 System.out.println("비교할 day : >>>>  "+day.substring(8,10));
	      Iterable<Reservation> res = reservationRepository.findAll(); // 대문자 수정!
	      List<Reservation> list2 = new ArrayList<>();
	      for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list2.add(dto1);
	      }
	      return list2.stream()
	    		  .sorted(Comparator.comparing(Reservation::getResseq).reversed())
	    		  .filter(t-> new SimpleDateFormat("yyyy-MM-dd").format(new Date(t.getResdate()))
	    				  .equals(day) ) //&& t.getResdate() < new Date().getTime()
	    		  .collect(Collectors.toList());
	   }
	 
	 @GetMapping("/weeklist")
	   public List<Reservation> weekList(){
	      Iterable<Reservation> res = reservationRepository.findAll(); // 대문자 수정!
	      List<Reservation> list2 = new ArrayList<>();
	      for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list2.add(dto1);
	      }
	      int week = 604800000;
	      return list2.stream()
	    		  .sorted(Comparator.comparing(Reservation::getResseq).reversed())
	    		  .filter(t-> new Date(t.getResdate()).getTime() >= (new Date().getTime()-week*1000) 
	    				  && new Date(t.getResdate()).getTime() <= (new Date().getTime()*1000))
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
	
	@GetMapping("/2")
	public List<Object> testlist(){
		Iterable<Reservation> res = reservationRepository.findAll();
		List<Reservation> list1 = new ArrayList<>();
		for(Reservation r : res) {
			Reservation dto1 = modelMapper.map(r, Reservation.class);
			list1.add(dto1);
		}
		System.out.println(list1.stream().map(Reservation::getResseq));
		return list1.stream().map(Reservation::getResseq).collect(Collectors.toList());
	}

}