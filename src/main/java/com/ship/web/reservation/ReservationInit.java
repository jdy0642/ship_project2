package com.ship.web.reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;

@Component
public class ReservationInit extends Proxy implements ApplicationRunner  {
	private ReservationRepository reservationrepository;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public ReservationInit(ReservationRepository reservationrepository) {
		this.reservationrepository = reservationrepository;
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = reservationrepository.count();
		if(count ==0) {
			Reservation res = null;
			String[][] mtx = {
					{"hong", "1", "2019-2-3", "3"},
					{"hong1", "11", "2019-2-31", "31"},
			};
			for(String[] arr : mtx) {
				res = new Reservation();
				res.setUserid(arr[0]);
				res.setStadiumid(arr[1]);
				res.setResdate(df.parse(arr[2]));
				res.setResnum(integer(arr[3]));
				reservationrepository.save(res);
			}
		}
	}
}
