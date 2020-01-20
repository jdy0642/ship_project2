package com.ship.web.kakao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
public class KakaoController {
	
	@Autowired
	private KakaoAPI kakao;
	
	@RequestMapping(value="/")
    public String index() {
        
        return "index";
    }
    
    @RequestMapping(value="/oauth")
    public String oauth(@RequestParam("code") String code) {
    	String access_Token = kakao.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
        return "index";
    }
}
