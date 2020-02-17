package com.ship.web.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;

@Component
public class PersonInit extends Proxy implements ApplicationRunner  {
	private PersonRepository personRepository;
	
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
					{"hong", "1", "홍성철", "010-6202-4102", "0", "관리자", "10","14","1","4","35","false","1","true","false","false","hongsc1991@naver.com", "Destiny"},
					{"hong1", "1135", "주동엽", "010-3493-3431", "0", "학생", "43","32","8","9","10","false","2","true","false","false","dfhd2f@naver.com", "미드차이 어쩔래"},
					{"aaaa", "145425", "여요한", "010-3431-1090", "0", "학생", "24","34","4","19","140","false","0","false","false","false","asdvw@naver.com", "yamine"},
					{"bbb", "1345", "서정욱", "010-3490-1431", "0", "변호사", "33","67","16","29","135","false","2","false","false","false","pica@naver.com", "hideonbush"},
					{"cssdf", "1457", "이정승", "010-6831-0980", "0", "개발자", "29","45","45","91","45","false","2","true","false","false","jiu@naver.com", "꿈의 남자"},
					{"dgndklf", "1869", "김수현", "010-5098-0531", "0", "학생", "28","58","55","13","1","false","1","true","false","false","brave@naver.com", "칼과창방패"},
					{"edsf", "1896", "손흥민", "010-3789-8431", "0", "검사", "25","435","8","119","155","false","2","false","false","false","thunder@naver.com", "초보원딜러"},
					{"fasdf", "1689", "박지성", "010-3431-8796", "0", "의사", "35","54","67","49","10","false","0","true","false","false","breastfire@naver.com", "Runaway Crush"},
					{"gadsf", "1789", "양동근", "010-0785-9831", "0", "가수", "27","24","4","29","111","false","2","false","false","false","shild@naver.com", "Roach"},
					{"hsdf", "1879", "김태희", "010-3786-4531", "0", "퍼블리셔", "40","71","33","14","56","false","2","false","false","false","asdf@naver.com", "The Marchen"},
					{"isdf", "1789", "장동건", "010-3095-4831", "0", "가수", "37","76","8","134","33","false","1","true","false","false","asdf@naver.com", "백수 정글"},
					{"jdfdf", "1989", "원빈", "010-3489-5631", "0", "선생님", "31","36","22","34","65","false","2","false","false","false","vvx@naver.com", "짐승마스크"},
					{"k", "125", "김미나", "010-3545-7897", "0", "축구선수", "19","346","8","11","11","false","2","false","false","false","sgfg@naver.com", "레오나는레모나"},
					{"ldfdf", "8678", "기무라", "010-1231-5631", "0", "기관사", "30","71","56","34","31","false","1","true","false","false","qwre@naver.com", "잘못해도욕하지마"},
					{"mdfdf", "56756", "서동현", "010-7876-8557", "0", "비서", "25","71","21","31","54","false","2","false","false","false","sgg@naver.com", "트롤협회대표"},
					{"ndfdf", "56756", "봉준호", "010-6785-5631", "0", "수영강사", "25","71","65","9","14","false","0","false","false","false","czvb@naver.com", "Y대빡빡이"},
					{"odfdf", "5674", "김광현", "010-9007-5675", "0", "학생", "43","71","18","67","121","false","2","true","false","false","zvzv@naver.com", "민 망"},
					{"pdfdf", "dgdfg", "류현진", "010-3453-5631", "0", "학생", "21","71","38","35","34","false","2","false","false","false","zasg@naver.com", "핏 찌"},
					{"qdfdf", "46346", "알리", "010-7899-5631", "0", "선생님", "28","71","42","9","141","false","2","false","false","false","jgkfd@naver.com", "아마도 구구"},
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
