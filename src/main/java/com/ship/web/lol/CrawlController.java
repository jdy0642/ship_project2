package com.ship.web.lol;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.proxy.Box;
import com.ship.web.proxy.CrawlProxy;

import com.ship.web.proxy.Proxy;
import com.ship.web.proxy.Trunk;

@RestController
@RequestMapping("/crawl")
@CrossOrigin(origins = "http://localhost:8081")
public class CrawlController {
	@Autowired CrawlProxy crawler;
	@Autowired Trunk<Object> trunk;
	@Autowired Box<Object> box;
	@Autowired Proxy pxy;
	
	
	@GetMapping("/summoner/userName={a}")
	public ArrayList<HashMap<String, String>> opgg(@PathVariable String a){
		System.out.println("크롤링 진입");
		return crawler.opggCrawling(a);
	}
}

	