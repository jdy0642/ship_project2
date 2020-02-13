package com.ship.web.futsal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.NoArgsConstructor;
@NoArgsConstructor
@Service
public class FutsalService {
	@Autowired FutsalRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	
	public List<Futsal> allList(){
		Iterable<Futsal> all = futsalMatchRepository.findAll();
		List<Futsal> list = new ArrayList<>();
		for(Futsal match : all) {
			Futsal dto = modelMapper.map(match, Futsal.class);
			list.add(dto);
		}	
		return list.stream().collect(Collectors.toList());
	}
	
	public List<String> stadiumNameList(){
		return allList().stream()
				.map(Futsal::getStadiumname)
				.collect(Collectors.toList());
	}

}
