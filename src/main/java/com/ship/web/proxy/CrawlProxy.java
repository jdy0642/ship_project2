package com.ship.web.proxy;

import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component("crawler")
@Lazy
public class CrawlProxy extends Proxy{
	@Autowired Box<HashMap<String, String>> box;
	@Autowired Trunk<String> trunk;
	
	public ArrayList<HashMap<String, String>> opggCrawling(String a){
		box.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "http://www.op.gg/summoner/userName="+a;
			Connection.Response page =
					Jsoup.connect(url)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = page.parse();
			Elements summonername = temp.select("span.Name");
			Elements rate = temp.select("div.WinRatioTitle");
			Elements most = temp.select("div.MostChampionContent");
			Elements rank = temp.select("div.TierRankInfo");
			Elements position = temp.select("td.PositionStats");
			Elements photo = temp.select("img.ChampionImage");
			HashMap<String, String> map = null;
			for(int i = 0; i<summonername.size(); i++) {
				map = new HashMap<>();
				map.put("summonername", summonername.get(i).text());
				map.put("rate", rate.get(i).text());
				map.put("most", most.get(i).text());
				map.put("rank", rank.get(i).text());
				map.put("position", position.get(i).text());
				map.put("photo", photo.get(i).select("img").attr("src"));
				
				box.add(map);
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return box.get();
	}
}
