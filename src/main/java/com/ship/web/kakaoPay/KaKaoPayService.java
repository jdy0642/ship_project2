package com.ship.web.kakaoPay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.ship.web.person.Person;
import com.ship.web.person.PersonRepository;

@Service
public class KaKaoPayService {
	private String tid;
	private String personseq;
	@Autowired private PersonRepository personRepository;
	@Autowired private Person person;
	
	public Map<String, String> req(String personseq){
		Map<String,String> map = new HashMap<String, String>();
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		final String kakaoKey = "KakaoAK 98fa824fb203f20a3caee0ed79a0203e";
		final String reDirect = "http://localhost:8081/kakaopay/respones";
		JSONObject json = null;
		String url = "https://kapi.kakao.com/v1/payment/ready?"
				+ "cid=TC0ONETIME&"
				+ "partner_order_id=1001&"
				+ "partner_user_id=test@test.com&"
				+ "item_name=풋살&"
				+ "quantity=1&"
				+ "total_amount=10000&"
				+ "tax_free_amount=0&"
				+ "approval_url="+ reDirect + "&"
				+ "fail_url=http://localhost:8081/fail&"
				+ "cancel_url=http://localhost:8081/cancel";
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.POST)
			.userAgent(USER_AGENT)
			.header("Authorization", kakaoKey)
			.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
			.ignoreContentType(true)
			.execute();
//			System.out.println(html.parse().body().text());
			json = new JSONObject(html.parse().body().text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(json.get("tid").toString());
		map.put("msg", "success");
		map.put("tid", json.get("tid").toString());
		tid = json.get("tid").toString();
		this.personseq =personseq;
		map.put("next_redirect_pc_url", json.get("next_redirect_pc_url").toString());
		return map;
	}
//	'cid': `TC0ONETIME`,
//    'tid': '3c831b1504c37788cff1',
//    'partner_order_id': '1001',
//    'partner_user_id': 'test@test.com',
//    'pg_token': this.$route.query.pg_token

	public Map<String, String> res(Map<String, String> res) {
		Map<String,String> map = new HashMap<String, String>();
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		final String kakaoKey = "KakaoAK 98fa824fb203f20a3caee0ed79a0203e";
		JSONObject json = null;
		String url = "https://kapi.kakao.com/v1/payment/approve?"
				+ "cid=TC0ONETIME&"
				+ "partner_order_id=1001&"
				+ "partner_user_id=test@test.com&"
				+ "tid="+tid+"&"
				+ "pg_token="+res.get("token");
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.POST)
						.userAgent(USER_AGENT)
			.header("Authorization", kakaoKey)
			.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
			.ignoreContentType(true)
			.execute();
			//System.out.println(html.parse().body().text());
			json = new JSONObject(html.parse().body().text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//System.out.println(json.get("tid").toString());
		map.put("msg", "success");
		map.put("tid", json.get("tid").toString());
		
		person = personRepository.findById(Long.parseLong(personseq)).get();
		person.setPoint(String.valueOf(Integer.parseInt(person.getPoint())+10000));
		personRepository.save(person);
		return map;
	}
}