package com.ship.web.kakaoPay;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.person.PersonRepository;
import com.ship.web.util.Constants;

@RequestMapping("/kakaopay")
@RestController
@CrossOrigin(origins = Constants.LOCAL)
public class KakaoPayController {
	@Autowired KaKaoPayService kakaoPayService;
	@Autowired private PersonRepository personRepository;
	
	@GetMapping("/request/{personseq}")
	public Map<String, String> request(@PathVariable String personseq) {
		return kakaoPayService.req(personseq);
	}
	
	@PostMapping("/respones")
	public Map<String, String> respones(@RequestBody Map<String, String> res) {
		return kakaoPayService.res(res);
	}

}
