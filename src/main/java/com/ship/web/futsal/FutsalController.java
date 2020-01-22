package com.ship.web.futsal;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/futsal")
@CrossOrigin(origins = "http://localhost:8081")
public class FutsalController {
	@Autowired FutsalMatchRepository futsalMatchRepository;
	@Autowired FutsalMatchService futsalMatchService;
	@Autowired ModelMapper futModelMapper;
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
		System.out.println(param);
		futsalMatchRepository.saveAll(param);
		System.out.println("insertdummy");
	}
}
