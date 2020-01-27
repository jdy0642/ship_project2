package com.ship.web.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.futsal.FutsalMatch;
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
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = reservationrepository.count();
		if(count < 4) {
			FutsalMatch fut = new FutsalMatch();
			fut.setFutsalmatchseq(Long.valueOf(33));
			Person person =  personRepository.findAll().iterator().next();
			
	
			Reservation res = null;
			String[][] mtx = {
					{"hong", "1", "2019-2-3", "3"},
					{"hong1", "11", "2019-2-31", "31"},
			};
			for(String[] arr : mtx) {
				res = new Reservation();
				//res.setFutsalmatchseq(Long.valueOf(arr[1]));
				res.setResdate(System.currentTimeMillis());
				//res.setFutsalmatchseq(fut);
				//res.setUserid(person);
				reservationrepository.save(res);
			}
		}
	}
}
