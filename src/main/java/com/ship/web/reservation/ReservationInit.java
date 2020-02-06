package com.ship.web.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.mysql.cj.ParseInfo;
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
		int initdummy = 9999;
		if(count < 100) {
			dummyres(initdummy);
		}
	}
	public void dummyres(int dummycount) {
		FutsalMatch fut = null;
		Person person = null;
		Reservation res = null;
		int personCount = (int) personRepository.count();
		int futsalCount = (int) futsalMatchRepository.count();
		List<Reservation> resList = new ArrayList<Reservation>();
		BiFunction<Integer, Integer, Double> f = (p,t) -> Math.random()*p - t;
		for(int i=0; i< dummycount; i++) {
			res = new Reservation();
			fut = new FutsalMatch();
			person = new Person();
			res.setResdate(System.currentTimeMillis()+ (long)(f.apply(30,10)*1000*3600*24));
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
