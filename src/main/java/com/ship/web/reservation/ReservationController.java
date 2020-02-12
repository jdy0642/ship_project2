package com.ship.web.reservation;
import com.ship.web.util.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ship.web.proxy.Proxy;
import com.ship.web.proxy.Trunk;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = Constants.LOCAL)
//@CrossOrigin(origins = Constants.H_S3)
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private Reservation reservation;
	@Autowired private ReservationService reservationService;
	@Autowired private FutsalMatchRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	@Autowired private Printer p;
	@Autowired private Proxy pxy;
	@Autowired private Trunk<Object> tr;
	@GetMapping("/1")
	public Iterable<Map<String, Object>> reslist(){
		return reservationService.reservationTable();
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
		return list1.stream().collect(Collectors.toList());
	}
	
	 @GetMapping("/onedaylist/{day}")
	   public List<Reservation> onedaylist(@PathVariable String day){
		 System.out.println("들어온 day : >>>>  "+day);
		 System.out.println("비교할 day : >>>>  "+day.substring(8,10));
	      Iterable<Reservation> res = reservationRepository.findAll();// 대문자 수정!
	      List<Reservation> list2 = new ArrayList<>();
	      for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list2.add(dto1);
	      }
	      return list2.stream()
	    		  .sorted(Comparator.comparing(Reservation::getResseq).reversed())
	    		  .filter(t-> new SimpleDateFormat("yyyy-MM-dd").format(t.getResdate())
	    				  .equals(day) ) //&& t.getResdate() < new Date().getTime()
	    		  .collect(Collectors.toList());
	   }
	 
//	 @GetMapping("/onedaylist/region={region}/day={day}")
//	   public Long onedaylist(@PathVariable String day,@PathVariable String region){
//		 System.out.println("들어온 day : >>>>  "+day);
//		 System.out.println("비교할 day : >>>>  "+day.substring(8,10));
//	      Iterable<Reservation> res = reservationRepository.findAll();// 대문자 수정!
//	      List<Reservation> list2 = new ArrayList<>();
//	      for(Reservation r : res) {
//	         Reservation dto1 = modelMapper.map(r, Reservation.class);
//	         list2.add(dto1);
//	      }
//	      return list2.stream()
//	    		  .sorted(Comparator.comparing(Reservation::getResseq).reversed())
//	    		  .filter(j->j.getFutsalmatchseq().getStadiumaddr().substring(0, 2).equals(region))
//	    		  .filter(t-> new SimpleDateFormat("yyyy-MM-dd").format(t.getResdate())
//	    				  .equals(day) ) //&& t.getResdate() < new Date().getTime()
//	    		  .collect(Collectors.counting());
//	   }
	 
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
	    		  .filter(t-> t.getResdate() > (new Date().getTime()-week*1000) 
	    				  && t.getResdate() <= (new Date().getTime()*1000))
	    		  .collect(Collectors.toList());
	   }
	 
	 @GetMapping("/region")
	 public Long partitionCountPerRegion(@PathVariable String region, @PathVariable Long resdate) {
			// 지역별 1등 구장
//		 reservation.getFutsalmatchseq().getStadiumaddr().substring(0,2).equals(region);
		 
		 return null;
	      
//	      public Long partioningCountPerGender(boolean gender) {
//	  		// 2.단순분할 (성별 학생수)
//	  		return personRepository.findByRole("student").stream()
//	  				.collect(
//	  						Collectors
//	  							.partitioningBy(Person::isMale,
//	  								Collectors.counting()))
//	  				.get(gender);
//	  	}
		}
	
	 @GetMapping("/mymatch/{personseq}")
	 public List<Reservation> myMatch(@PathVariable Long personseq) {
		 p.accept("마이매치");
		 Person person = new Person();
		 person.setPersonseq(personseq);
		 Iterable<Reservation> res =  reservationRepository.findByPersonseq(person);
		 List<Reservation> list3 = new ArrayList<>();
		 for(Reservation r : res) {
	         Reservation dto1 = modelMapper.map(r, Reservation.class);
	         list3.add(dto1);
	      }
		 return list3.stream()
				 .sorted(Comparator.comparing(Reservation::getResseq).reversed())
				 .limit(5).collect(Collectors.toList());
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
	
	@PostMapping("/testres")
	public String connecttest() {
	
		
		return "/pay";
	}

	@GetMapping("/2")
	public Iterable<Map<String, Object>> testlist(){
		return reservationService.reservationTable();
	}
}