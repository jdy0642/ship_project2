package com.ship.web.lol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;import java.util.stream.Collector;
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

import com.ship.web.proxy.Box;
import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.Proxy;
import com.ship.web.proxy.Trunk;
import com.ship.web.util.Printer;

@RestController
@RequestMapping("/lol")
@CrossOrigin(origins = "http://localhost:8081")
public class LolController {
	@Autowired CrawlProxy crawler;
	@Autowired Trunk<Object> trunk;
	@Autowired Box<Object> box;
	@Autowired Proxy pxy;
	@Autowired Printer p;
	@Autowired ModelMapper modelMapper;
	@Autowired LolRepository lolRepository; // test commit
	
	@GetMapping("/summoner/userName={summonername}")
	public ArrayList<HashMap<String, String>> opgg(@PathVariable String summonername){
		System.out.println("방생성 시 크롤링 db 데이터 진입"+summonername);
		return crawler.opggCrawling(summonername);
	}
	
	@DeleteMapping("/delete/{cardseq}")
	public void withdrawal(@PathVariable Long cardseq) {
		p.accept("방탈 진입");
		lolRepository
		.delete(lolRepository
				.findByCardseq(cardseq));
	}
	@PutMapping("/update/{cardseq}")
	public void modify(@RequestBody Lol lol, @PathVariable Long cardseq) {
		p.accept("수정 진입");
		lol = lolRepository.save(lolRepository.findByCardseq(cardseq));
	}
	
	@GetMapping("/listpage={page}")
	public List<Lol> roomlist(@PathVariable int page){
		System.out.println(page);
		Iterable<Lol> entites = lolRepository.findAll();
		List<Lol> list = new ArrayList<>();
		Date date = new Date();
		
		for(Lol l : entites) {
			Lol dto = modelMapper.map(l,Lol.class);
			list.add(dto);
		}
		return list.stream()
				.sorted(Comparator.comparing(Lol::getCardseq)
				.reversed()) 
				.limit(page*9) 
				.collect(Collectors.toList()); 
	}
	
	@PostMapping("/createroom")
	public HashMap<String, Object> createroom(@RequestBody Lol lol){
		p.accept("방 생성 컨트롤러 진입"+lol);
		HashMap<String, Object> map = new HashMap<>();
		String[] img = {
				"ahri",
				"Ashe",
				"garen",
				"jarvaniv",
				"Jinx",
				"Leona",
				"Lux",
				"missfortune",
				"neeko",
				"sona",
				"Teemo",
				"xayah",
				"xinzhao",
				"Yasuo",
				"zoe",
		};
		
		lol.setImgurl(img[pxy.random(0, 15)]);
		lol = lolRepository.save(lol);
		if(lol != null) {
			map.put("result","SUCCESS");
			map.put("lol",lol);
		}else {
			map.put("result","FAIL");
		}
		return map;
	}
}

	