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
	
	public ArrayList<HashMap<String, String>> opggCrawling(String lolname){
		box.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "http://www.op.gg/summoner/userName="+lolname;
			Connection.Response page =
					Jsoup.connect(url)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = page.parse();
			Elements photo = temp.select("img.ChampionImage");
			Elements summonername = temp.select("span.Name");
			Elements tier = temp.select("div.TierRank");
			Elements rate = temp.select("div.TierInfo");
			Elements most = temp.select("div.MostChampionContent");
			Elements position = temp.select("td.PositionStats");
			HashMap<String, String> map = null;
				map = new HashMap<>();
				map.put("summonername", summonername.get(0).text());
				map.put("tier", tier.get(0).text());
				map.put("rate", rate.get(0).text());
				map.put("most", most.get(0).text());
				map.put("position", position.get(0).text());
				map.put("photo", photo.get(0).select("img").attr("src"));
				box.add(map);
				
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return box.get();
	}
}
