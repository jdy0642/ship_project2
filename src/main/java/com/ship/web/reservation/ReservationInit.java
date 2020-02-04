package com.ship.web.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.futsal.FutsalMatch;
import com.ship.web.futsal.FutsalMatchRepository;
import com.ship.web.person.Person;
import com.ship.web.person.PersonRepository;
import com.ship.web.proxy.Proxy;

@Component
public class ReservationInit extends Proxy implements ApplicationRunner  {
	private ReservationRepository reservationrepository;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public ReservationInit(ReservationRepository reservationrepository) {
		this.reservationrepository = reservationrepository;
	}
	@Autowired private PersonRepository personRepository;
	@Autowired private FutsalMatchRepository futsalMatchRepository;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = reservationrepository.count();
		if(count < 100) {
			FutsalMatch fut = null;
			Person person = null;
			Reservation res = null;
			int personCount = (int) personRepository.count();
			int futsalCount = (int) futsalMatchRepository.count();
			List<Reservation> resList = new ArrayList<Reservation>();
			for(int i=0; i< 1000; i++) {
				res = new Reservation();
				fut = new FutsalMatch();
				person = new Person();
				res.setResdate(System.currentTimeMillis()+random(-7, 14*3600*1000*24));
				fut.setFutsalmatchseq((long) random(1,futsalCount-1));
				person.setPersonseq((long) random(1, personCount-1));
				res.setFutsalmatchseq(fut);
				res.setPersonseq(person);
				res.setKm(random(5,20));
				res.setScore(random(0,4));
				res.setWin(Arrays.asList("win","lose").get(random(0,1)));
				resList.add(res);
			}
			reservationrepository.saveAll(resList);
		}
	}
}
