package com.ship.web.kakaoPay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ship.web.person.PersonService;
import com.ship.web.util.Constants;

@Service
public class KaKaoPayService {
	@Autowired PersonService personService;
	
	public Map<String, String> req(String personseq, String value){
		Map<String,String> map = new HashMap<String, String>();
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		final String kakaoKey = "KakaoAK 98fa824fb203f20a3caee0ed79a0203e";
		JSONObject json = null;
		String url = "https://kapi.kakao.com/v1/payment/ready?"
				+ "cid=TC0ONETIME&"
				+ "partner_order_id=1001&"
				+ "partner_user_id=test@test.com&"
				+ "item_name=풋살&"
				+ "quantity=99&"
				+ "total_amount="+value+"&"
				+ "tax_free_amount=0&"
				+ "approval_url="+ Constants.LOCAL + "/mypage&"
				+ "fail_url="+Constants.LOCAL+"&"
				+ "cancel_url="+Constants.LOCAL;
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.POST)
			.userAgent(USER_AGENT)
			.header("Authorization", kakaoKey)
			.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
			.ignoreContentType(true)
			.execute();
			json = new JSONObject(html.parse().body().text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("msg", "success");
		map.put("tid", json.get("tid").toString());
		map.put("next_redirect_pc_url", json.get("next_redirect_pc_url").toString());
		return map;
	}

	public Map<String, Object> res(Map<String, String> res) {
		Map<String,Object> map = new HashMap<String, Object>();
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		final String kakaoKey = "KakaoAK 98fa824fb203f20a3caee0ed79a0203e";
		JSONObject json = null;
		String url = "https://kapi.kakao.com/v1/payment/approve?"
				+ "cid=TC0ONETIME&"
				+ "partner_order_id=1001&"
				+ "partner_user_id=test@test.com&"
				+ "tid="+res.get("tid")+"&"
				+ "pg_token="+res.get("token");
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.POST)
						.userAgent(USER_AGENT)
			.header("Authorization", kakaoKey)
			.header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
			.ignoreContentType(true)
			.execute();
			json = new JSONObject(html.parse().body().text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("msg", "success");
		personService.updatePoint(res.get("personseq"),json.getJSONObject("amount").get("total").toString());
		map.put("person", personService.findBypersonseq(res.get("personseq")));
		return map;
	}
}