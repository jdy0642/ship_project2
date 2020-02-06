package com.ship.web.futsal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.Proxy;
import com.ship.web.util.Constants;

@RestController
@RequestMapping("/futsal")
@CrossOrigin(origins = Constants.LOCAL)
//@CrossOrigin(origins = Constants.J_S3)
public class FutsalController {
	@Autowired FutsalMatchRepository futsalMatchRepository;
	@Autowired FutsalMatchService futsalMatchService;
	@Autowired ModelMapper futModelMapper;
	@Autowired FutsalMatch fut;
	@Bean
	public ModelMapper futModelMapper() {return new ModelMapper();}
	
	@GetMapping("/")
	public List<FutsalMatch> findAll(){
		Iterable<FutsalMatch> all = futsalMatchRepository.findAll();
		List<FutsalMatch> list = new ArrayList<>();
		for(FutsalMatch match : all) {
			FutsalMatch dto = futModelMapper.map(match, FutsalMatch.class);
			list.add(dto);
		}
		System.out.println("findAll");
		return list.stream().collect(Collectors.toList());
	}
	
	@PostMapping("/insertdummy")
	public void insertDummy(@RequestBody List<FutsalMatch> param) {
		futsalMatchRepository.saveAll(param);
		System.out.println("insertdummy");
	}
	
	@GetMapping("/match/{matchId}")
	public FutsalMatch selectMatch(@PathVariable Long matchId) {
		return futsalMatchRepository.findById(matchId).get();
	}
	
	@PutMapping("/match/{matchId}")
	public void updateMatch(@PathVariable Long matchId){
		fut = futsalMatchRepository.findById(matchId).get();
		fut.setRemain(fut.getRemain()-1);
		futsalMatchRepository.save(fut);
	}
	
	@PostMapping("/register")
	public boolean createMath(@RequestBody FutsalMatch match) {
		futsalMatchRepository.save(match);
		return true;
	}
	
	@GetMapping("/test")
	public Map<?, ?> test(){
		Map<String, String> map = new HashMap<String, String>();
		map.put("msg", String.valueOf(System.currentTimeMillis()));
		CrawlProxy cPxy = new CrawlProxy();
		cPxy.kakaoCrawlFutMatch("서울 풋살장",1);
		return map;
	}
}
