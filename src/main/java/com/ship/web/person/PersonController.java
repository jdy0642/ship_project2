package com.ship.web.person;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.PageProxy;
import com.ship.web.util.Constants;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = Constants.LOCAL)

public class PersonController {
	@Autowired private PersonRepository personRepository;
	@Autowired PersonService personService;
	@Autowired ModelMapper modelMapper;
	@Autowired CrawlProxy crawler;
	@Autowired PageProxy pager;
	@Bean public ModelMapper modelMapper() {return new ModelMapper();}
	
	@RequestMapping("/")
	public String index() {
		Iterable<Person> all = personRepository.findAll();
		StringBuilder sb = new StringBuilder();
		all.forEach(p -> sb.append(p.getName()+" "));
		return sb.toString();
	}

	@PostMapping("/login")
	public HashMap<String, Object> login(@RequestBody Person person) {
		HashMap<String, Object> map = new HashMap<>();
		person = personRepository.findByUseridAndPasswd
					(person.getUserid(), person.getPasswd());
		if(person!= null) {
			map.put("result","SUCCESS");
			map.put("person",person);
		}else {
			map.put("result","FAIL");
			map.put("person",person);
		}
		return map;
	}
	
	@GetMapping("/idcheck/{userid}")
	   public String idcheck(@PathVariable String userid) {
	      String result = "";
	      Person person = null;
	      person = personRepository.findByUserid(userid);
	      if(person!= null) {
	         result = "SUCCESS";
	      }else {
	    	  result = "FAIL";
	      }
	      return result;
	   }
	@PostMapping("/blackcheck/{userid}")
	   public HashMap<String, Object> blackCheckById(@PathVariable String userid) throws ParseException {
	      HashMap<String, Object> map= new HashMap<>();
	      Person person = null;
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	      person = personRepository.findByUserid(userid);
			  if(person.isFutblack() == true) {
					if(sdf.parse(person.getBlacktime()).getTime() <= new Date().getTime()) {
					deleteBlack(userid);
					map.put("result","FAIL");
					  map.put("blacktime",null);
					}else {
						map.put("result","SUCCESS");
					      map.put("blacktime",person.getBlacktime());
					      map.put("blackreason",person.getBlackreason());
					}
				  
			  }else {
				  map.put("result","FAIL");
				  map.put("blacktime",null);
			  }
	      return map;
	   }
	
	@PostMapping("/join")
	public HashMap<String, Object> join(@RequestBody Person person) {
		HashMap<String, Object> map = new HashMap<>();
		person = personRepository.save(person);
		if(person!= null) {
			map.put("result","SUCCESS");
			map.put("person",person);
		}else {
			map.put("result","FAIL");
			map.put("person",person);
		}
		return map;
	}
	
	@PutMapping("/save/{userid}")
	   public void modify(@RequestBody Person person, @PathVariable String userid) {
		Person temp = personRepository.findByUserid(userid);
			temp.setEmail(person.getEmail());
			temp.setPasswd(person.getPasswd());
			temp.setSummonername(person.getSummonername());
			temp.setTel(person.getTel());
			temp.setInterest(person.getInterest());
			personRepository.save(temp);
	   }
	
	@DeleteMapping("/withdrawal/{userid}")
	public void withdrawal(@PathVariable String userid) {
		personRepository
		.delete(personRepository
				.findByUserid(userid));
	}
	
	@GetMapping("/customermanage")
	public List<Person> memberlist(){
		Iterable<Person> entites = personRepository.findAll();
		List<Person> list = new ArrayList<>();
		for(Person p : entites) {
			Person dto = modelMapper.map(p, Person.class);
			list.add(dto);
		}
		return list.stream().sorted(Comparator.comparing(Person::getPersonseq).reversed()).collect(Collectors.toList());
	}
	@PutMapping("/addBlack/{userid}/{blacktime}/{blackreason}")
	public void addBlackList(@PathVariable String userid,@PathVariable String blacktime,@PathVariable String blackreason) {
		Person temp = personRepository.findByUserid(userid);
			temp.setFutblack(true);
			temp.setBlacktime(blacktime);
			temp.setBlackreason(blackreason);
			personRepository.save(temp);
	}
	
	@PutMapping("/deleteBlack/{userid}")
	public void deleteBlack(@PathVariable String userid) {
		Person temp = personRepository.findByUserid(userid);
			temp.setFutblack(false);
			temp.setBlacktime(null);
			temp.setBlackreason(null);
			personRepository.save(temp);
	}
	

	@PutMapping("/update/{userid}")
	public void update(@RequestBody Person person, @PathVariable String userid) {
		person = personRepository.save(person);
	}
}