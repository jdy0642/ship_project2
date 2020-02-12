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
public class FutsalMatchService {
	@Autowired FutsalMatchRepository futsalMatchRepository;
	@Autowired ModelMapper modelMapper;
	
	public List<FutsalMatch> allList(){
		Iterable<FutsalMatch> all = futsalMatchRepository.findAll();
		List<FutsalMatch> list = new ArrayList<>();
		for(FutsalMatch match : all) {
			FutsalMatch dto = modelMapper.map(match, FutsalMatch.class);
			list.add(dto);
		}	
		return list.stream().collect(Collectors.toList());
	}
	
	public List<String> stadiumNameList(){
		return allList().stream()
				.map(FutsalMatch::getStadiumname)
				.collect(Collectors.toList());
	}

}
