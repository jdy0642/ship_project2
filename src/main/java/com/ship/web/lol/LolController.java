package com.ship.web.lol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	@Autowired LolRepository lolRepository;
	
	@GetMapping("/summoner/userName={lolname}")
	public ArrayList<HashMap<String, String>> opgg(@PathVariable String lolname){
		System.out.println("크롤링 진입");
		return crawler.opggCrawling(lolname);
	}
	
	@GetMapping("/list")
	public List<Lol> roomlist(){
		Iterable<Lol> entites = lolRepository.findAll();
		List<Lol> list = new ArrayList<>();
		for(Lol l : entites) {
			Lol dto = modelMapper.map(l,Lol.class);
			list.add(dto);
		}
		return list.stream().collect(Collectors.toList());
	}
	
	
	@PostMapping("/createroom")
	public HashMap<String, Object> createroom(@RequestBody Lol lol){
		p.accept("롤 컨트롤러 진입"+lol);
		HashMap<String, Object> map = new HashMap<>();
		String[] img = {
				"https://w.namu.la/s/65e9c3777f6ccdb9e462635fd238f9bc30f2248bf4bff65178a31d8114f8f841fccdff26d0ef4b9a9f89698ab098c73fb54fb505b967ff6519dba358eabfdac15953d557f2dad09497236c50aaaf9f913b5feb26ef546432b8c3691bee906d5c6847f42160a7f7e9f4a7c3b621b88717",
				"https://w.namu.la/s/b4fe89a2dc24d174ebba268d870c8672b0c40faaf4df07d0b36cc5ee8f5f40efe96007b2d3521aa6ca5805f4dce381f7914261627151c3627448d147982b3a87770cbdd59d21e76d2f64d040fcfe036f213102d46ef94824a269663dac73f262",
				"https://w.namu.la/s/0f03c257f252cb25791122b542f3c0b47d0d9c4631bc80d927f39836fefbc5f55247c28bda1ac910e6ffc2f278e3f198a1d940817bb752b1b80610703bb9511d250993cd3c998cb1699dab4535214332ac2cafb05f787bacf6d111bb8a94bb7cb5661acb2d409bd69cfa2e769b9d67ab",
				"https://ww.namu.la/s/8b0a7e865d8841c425fc4f27b20c48281920bc574cffd4b9d014b867fdf443d391b841f307ca25fd2cf012758ef0ad9e312e3f12c929ba509a088075dcd0394ca839bf1a70fe53b0ff03e64eb409a1926a6710e30c532fc0e6c52f5a6e4909e5089bd055350211482a80fea3bdf8c6a7",
				"https://ww.namu.la/s/6b4b67c5190df5f22ca4d4124e40901f80efa0e6bf5b9755c1daf5a6772a4664eed813cc049159c991c8981f46e728e5a2709f0fcaf05f1ee48f029d5d24eef98dcabc26a4f169a96b5c234b89c94fcabc634e7c2d853e91590f18235966cfda7763743d216e844a976501fed23e5daf",
				"https://ww.namu.la/s/11bec8b4ee21de602723e20b09fa8f5932a2ee30a65d53e6d78f83a267cd9290568719408ffe51fca59ebafbb5a79a1b52279ebb1f72a2bdcab0a33a768817e223cc9e121bdaf651965f0dcb1b5385e5de3c4f46065f380c8412abe68ce41046",
				"https://w.namu.la/s/1fc27c1d670b69a53eba9d78e1c7e329ad2121f1a430113df7cf63679bae8d68efbfab7a26d3eda4bec5e61cf6433fc0c188853703d5489d35196166c4437a293683547c649c99ed350ff400980dc1c0e9eaba7ae294ea49814832f500fe57b9bd34b82fff3d985197fefce3a6e8d13c",
				"https://ww.namu.la/s/6ec8a4f0bf9f953d88bd428255682d842b81ea626dcdf9167c6f9d5512e16745f68ccb3b3a327c5f4778f74842a1e37e7548a958dd4c0d2f4822a76dce2004a2bbe0197c8b4af65d4559ab8326eae27759a1c6cfb62e37ebb321f7fa669c51ff93209c8678c24875f6a32ee04b2f5278",
				"https://ww.namu.la/s/7732e6b2dd01706dc0d59646d71f0860277b8d88bfc0e4e99b688f22d421baaf189f47644b4eb0f9e0829e9889b30be15b8c0756b5bb4528d7c784c2481e96f9376c0839add98c173f7bcba8b6f5034cc27d67f8c00a6146ea6157b1cf6ec9c8b16e924ff5ffa79927d4d44168dab3a6"
				
		};
		lol.setImgurl(img[pxy.random(0, 9)]);
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

	