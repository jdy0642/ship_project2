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
					{"hong", "1", "홍길동", "1980-01-01", "true", "1", "1", "100", "teacher"},
					{"kim", "1", "김연아", "1980-01-02", "false", "1", "2", "90", "student"},
					{"jeon", "1", "전지현", "1980-01-03", "false", "1", "3", "80", "student"},
					{"son", "1", "손흥민", "1980-01-05", "true", "1", "4", "40", "student"},
					{"cha", "1", "차범근", "1980-01-06", "true", "2", "1", "30", "student"},
					{"soo", "1", "김수현", "1982-01-01", "true", "2", "2", "20", "student"},
					{"seo", "1", "서현진", "1983-01-01", "false", "2", "3", "100", "student"},
					{"na", "1", "나상호", "1985-01-01", "true", "2", "4", "80", "student"},
					{"david", "1", "다비드", "1991-01-01", "true", "3", "1", "100", "student"},
					{"mina", "1", "미나", "1982-01-01", "false", "3", "2", "80", "student"},
					{"deok", "1", "덕배", "1983-01-01", "true", "3", "3", "10", "student"},
					{"ggul", "1", "꿀탱탱", "1986-01-01", "true", "3", "4", "30", "student"},
					{"han", "1", "김한솥", "1989-01-01", "false", "4", "1", "10", "student"},
					{"nami", "1", "홍나미", "1930-01-01", "false", "4", "2", "50", "student"},
					{"manner", "1", "나매너", "1983-01-01", "true", "4", "3", "100", "student"},
					{"poong", "1", "김풍기", "1989-01-01", "false", "4", "4", "70", "student"},
					{"lol", "1", "이롤", "1980-01-01", "true", "1", "1", "100", "student"},
					{"choon", "1", "공화춘", "1983-01-01", "false", "2", "1", "10", "student"},
					{"zzam", "1", "홍짬뽕", "1980-01-01", "true", "3", "1", "100", "student"},
					{"sin", "1", "신라면", "1983-01-01", "false", "4", "1", "40", "student"},
					{"hap", "1", "합정", "1980-01-01", "true", "3", "1", "100", "student"},
					{"dae", "1", "홍대", "1985-01-01", "false", "2", "1", "20", "student"},
			};
			for(String[] arr : mtx) {
				person = new Person();
					person.setUserid(arr[0]);
					person.setPasswd(arr[1]);
					person.setName(arr[2]);
					person.setBirthday(df.parse(arr[3]));
					person.setMale(Boolean.parseBoolean(arr[4]));
					person.setHak(integer(arr[5]));
					person.setBan(integer(arr[6]));
					person.setScore(integer(arr[7]));
					person.setRole(arr[8]);
					personRepository.save(person);
			}
		}
	}
}
