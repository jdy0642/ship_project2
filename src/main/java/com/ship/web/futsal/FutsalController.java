package com.ship.web.futsal;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ship.web.util.Constants;

@RestController
@RequestMapping("/futsal")
@CrossOrigin(origins = Constants.LOCAL)
public class FutsalController {
	@Autowired FutsalRepository futsalMatchRepository;
	@Autowired FutsalService futsalMatchService;
	@Autowired ModelMapper futModelMapper;
	
	@Bean
	public ModelMapper futModelMapper() {return new ModelMapper();}
	
	@GetMapping("/")
	public List<Futsal> findAll(){
		return futsalMatchService.allList();
	}
	
	@PostMapping("/insertdummy")
	public void insertDummy(@RequestBody List<Futsal> param) {
		futsalMatchRepository.saveAll(param);
	}
	
	@GetMapping("/match/{matchId}")
	public Futsal selectMatch(@PathVariable Long matchId) {
		return futsalMatchRepository.findById(matchId).get();
	}
	
	@PutMapping("/match/{matchId}")
	public void updateMatch(@PathVariable Long matchId){
		Futsal fut = futsalMatchRepository.findById(matchId).get(); 
		fut.setRemain(fut.getRemain()-1);
		futsalMatchRepository.save(fut);
	}
	
	@PostMapping("/register")
	public boolean createMath(@RequestBody Futsal match) {
		futsalMatchRepository.save(match);
		return true;
	}
}
