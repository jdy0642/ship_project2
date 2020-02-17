package com.ship.web.reservation;
import com.ship.web.util.Constants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
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
import com.ship.web.futsal.FutsalRepository;
import com.ship.web.person.Person;
import com.ship.web.person.PersonService;

@RestController
@RequestMapping("/res")
@CrossOrigin(origins = Constants.LOCAL)
//@CrossOrigin(origins = Constants.H_S3)
public class ReservationController {
	@Autowired private ReservationRepository reservationRepository;
	@Autowired private ReservationService reservationService;
	@Autowired private FutsalRepository futsalRepository;
	@Autowired private PersonService personService;
	@Autowired ModelMapper modelMapper;
	@GetMapping("/1")
	
	public Iterable<Map<String, Object>> reslist(){
		return reservationService.reservationTable();
	}
	
	 @GetMapping("/onedaylist/{day}")
	   public List<Reservation> onedaylist(@PathVariable String day){
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
	    		  .filter(t-> t.getResdate() >= (new Date().getTime()-week*1000) 
	    				  && t.getResdate() < (new Date().getTime()*1000))
	    		  .collect(Collectors.toList());
	   }
	 
	
	 @GetMapping("/mymatch/{personseq}")
	 public List<Reservation> myMatch(@PathVariable Long personseq) {
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
		Reservation res = new Reservation();
		res.setPersonseq(person);
		personService.updatePoint(String.valueOf(person.getPersonseq()), "-10000");
		res.setFutsal(futsalRepository.findById(matchId).get());
		res.setResdate(System.currentTimeMillis());
		reservationRepository.save(res);
		return reservationRepository.findByResdate(res.getResdate()) != null;
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
	public Iterable<Map<String, Object>> testlist(){
		return reservationService.reservationTable();
	}
	
}