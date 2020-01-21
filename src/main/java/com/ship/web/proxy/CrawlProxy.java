package com.ship.web.proxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.ship.web.futsal.FutsalMatch;

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
	
	public List<Trunk<String>> crawlFutMatch(int page){
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		String url = "https://map.naver.com/v5/api/search?caller=pcweb&query=풋살장&type=all&page="+page+"&displayCount=100&isPlaceRecommendationReplace=true&lang=ko";
		List<Trunk<String>> list = new ArrayList<>();
		JSONObject json = null;
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.GET)
			.userAgent(USER_AGENT)
			.ignoreContentType(true)
			.execute();
			//System.out.println(html.toString());
			json = new JSONObject(html.parse().select("body").text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArr = json.getJSONObject("result")
				.getJSONObject("place")
				.getJSONArray("list");
		for(int i = 0; i < jsonArr.length()-1; i++) {
			JSONObject j = jsonArr.getJSONObject(i);
			trunk.put(Arrays.asList("name","address","tel"),
					Arrays.asList(j.get("name").toString(),
							j.get("address").toString(),
							j.get("tel").toString()));
			list.add(trunk);
		}
		System.out.printf("%d 페이지 완료\n",page);
		return list;
	}
}