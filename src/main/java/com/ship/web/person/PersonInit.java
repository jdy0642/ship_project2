package com.ship.web.person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;

@Component
public class PersonInit extends Proxy implements ApplicationRunner  {
	private PersonRepository personRepository;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	public PersonInit(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = personRepository.count();
		if(count ==0) {
			Person person = null;
			String[][] mtx = {
					{"hong", "1", "홍성철", "010-6202-4102", "0", "admin", "30","1","1","1","0","false","1","true","false","false","hongsc1991@naver.com", "Destiny"},
					{"hong1", "1", "파이리", "31", "41", "teacher1", "22","71","8","9","10","false","2","true","false","false","dfhd2f@naver.com", "미드차이 어쩔래"},
					{"aaaa", "1", "라이츄", "31", "41", "teacher1", "24","71","8","9","10","false","2","false","false","false","asdvw@naver.com", "yamine"},
					{"bbb", "1", "피카츄", "31", "41", "teacher1", "37","71","8","9","10","false","2","false","false","false","pica@naver.com", "hideonbush"},
					{"a", "1", "지우", "31", "41", "teacher1", "32","71","8","9","10","false","2","true","false","false","jiu@naver.com", "꿈의 남자"},
					{"b", "1", "다간", "31", "41", "teacher1", "19","71","8","9","10","false","2","true","false","false","brave@naver.com", "내가강만식"},
					{"c", "1", "우뢰매", "31", "41", "teacher1", "40","71","8","9","10","false","2","false","false","false","thunder@naver.com", "초보원딜러"},
					{"d", "1", "마징가", "31", "41", "teacher1", "27","71","8","9","10","false","2","true","false","false","breastfire@naver.com", "Runaway Crush"},
					{"e", "1", "메칸더", "31", "41", "teacher1", "26","71","8","9","10","false","2","false","false","false","shild@naver.com", "Roach"},
					{"f", "1", "김태희", "31", "41", "teacher1", "25","71","8","9","10","false","2","false","false","false","shild@naver.com", "The Marchen"},
					{"g", "1", "장동건", "31", "41", "teacher1", "30","71","8","9","10","false","2","false","false","false","shild@naver.com", "백수 정글"},
					{"h", "1", "원빈", "31", "41", "teacher1", "28","71","8","9","10","false","2","false","false","false","shild@naver.com", "짐승마스크"},
			};
			for(String[] arr : mtx) {
				person = new Person();
					person.setUserid(arr[0]);
					person.setPasswd(arr[1]);
					person.setName(arr[2]);
//					person.setBirthday(df.parse(arr[3]));
					person.setTel(arr[3]);
					person.setPoint(arr[4]);
					person.setJob(arr[5]);
					person.setAge(integer(arr[6]));
					person.setScore(integer(arr[7]));
					person.setMvp(integer(arr[8]));
					person.setWin(integer(arr[9]));
					person.setKm(integer(arr[10]));
					person.setBookmark(Boolean.parseBoolean(arr[11]));
					person.setInterest(arr[12]);
					person.setMale(Boolean.parseBoolean(arr[13]));
					person.setLolblack(Boolean.parseBoolean(arr[14]));
					person.setFutblack(Boolean.parseBoolean(arr[15]));
					person.setEmail(arr[16]);
					person.setSummonername(arr[17]);
					personRepository.save(person);
			}
		}
	}
}
