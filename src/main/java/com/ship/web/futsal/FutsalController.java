package com.ship.web.futsal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/futsal")
@CrossOrigin("http://localhost:8081")
public class FutsalController {
	@Autowired FutsalMatchRepository futsalMatchRepository;
	@Autowired FutsalMatchService futsalMatchService;
	@Autowired ModelMapper footModelMapper;
	@Bean public ModelMapper footModelMapper() {return new ModelMapper();}
	
	@GetMapping("/")
	public List<FutsalMatch> findAll(){
		Iterable<FutsalMatch> all = futsalMatchRepository.findAll();
		List<FutsalMatch> list = new ArrayList<>();
		for(FutsalMatch match : all) {
			FutsalMatch dto = footModelMapper.map(match, FutsalMatch.class);
			list.add(dto);
		}
		return list.stream().collect(Collectors.toList());
	}
	
	@PostMapping("/insertdummy")
	public void insertDummy(@RequestBody FutsalMatch param) {
		System.out.println(param);
	}
	

}
