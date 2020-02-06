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
@Component("crawler")
@Lazy
public class CrawlProxy extends Proxy{
	@Autowired Box<HashMap<String, String>> box;
	@Autowired Trunk<String> trunk;
	public ArrayList<HashMap<String, String>> opggCrawling(String summonername){
		box.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "http://www.op.gg/summoner/userName="+summonername;
			Connection.Response page =
					Jsoup.connect(url)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = page.parse();
			Elements photo = temp.select("img.ChampionImage");
			Elements tier = temp.select("div.TierRank");
			Elements rate = temp.select("div.TierInfo");
			Elements most = temp.select("div.MostChampionContent");
			Elements position = temp.select("td.PositionStats");
			HashMap<String, String> map = null;
				map = new HashMap<>();
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
	public ArrayList<HashMap<String, String>> loltitleCrawling(int page){
		box.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "https://talk.op.gg/s/lol/lfg?page="+page;
			Connection.Response pagetemp =
					Jsoup.connect(url)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = pagetemp.parse();
			Elements title = temp.select("section[class=\"article-list article-list--compact\"] div[class=\"article-list-item__title\"] span");
//			Elements contents = temp.select("div[class=\"article-content\"] p");
			HashMap<String, String> map = null;
			for(int i=0; i<40;i++) {
				map = new HashMap<>();
				map.put("title", title.get(i).text());
//				map.put("contents", contents.get(0).text());
				box.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("loltitle 크롤링("+box.size()+")\n"+page+"/5");
		return box.get();
	}
	public ArrayList<HashMap<String, String>> lolidCrawling(int page){
		box.clear();
		try {
			final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
			String url = "https://www.op.gg/ranking/level/page="+page;
			Connection.Response pagetemp =
					Jsoup.connect(url)
					.method(Connection.Method.GET)
					.userAgent(USER_AGENT)
					.execute();
			Document temp = pagetemp.parse();
			Elements rhost = temp.select("td[class=\"ranking-table__cell ranking-table__cell--summoner\"] a span");
			Elements crawltier = temp.select("td[class=\"ranking-table__cell ranking-table__cell--tier\"]");
			Elements crawlrate = temp.select("td[class=\"ranking-table__cell ranking-table__cell--winratio\"] div span");
			HashMap<String, String> map = null;
			for(int i=0;i<40;i++) {
				map = new HashMap<>();
					map.put("rhost", rhost.get(i).text());
					map.put("crawltier", crawltier.get(i).text());
					map.put("crawlrate", crawlrate.get(i).text());
//					System.out.println(i+"번쨰 id"+rhost.get(i).text());
//					System.out.println(i+"번쨰 crawltier"+crawltier.get(i).text());
//					System.out.println(i+"번쨰 crawlrate \n"+crawlrate.get(i).text());
					box.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return box.get();
	}
	public List<Map<String, String>> crawlFutMatch(int page){
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		String url = "https://map.naver.com/v5/api/search?caller=pcweb&query=풋살장&type=all&page="+page+"&displayCount=100&isPlaceRecommendationReplace=true&lang=ko";
		List<Map<String, String>> list = new ArrayList<>();
		JSONObject json = null;
		Map<String, String> map = null;
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.GET)
			.userAgent(USER_AGENT)
			.ignoreContentType(true)
			.execute();
			System.out.println(html.toString());
			System.out.println("------------------------------------------------------------------------------------------");
			json = new JSONObject(html.parse().select("body").text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArr = json.getJSONObject("result")
				.getJSONObject("place")
				.getJSONArray("list");
		for(int i = 0; i < jsonArr.length()-1; i++) {
			map = new HashMap<>();
			JSONObject j = jsonArr.getJSONObject(i);
			map.put("name",j.get("name").toString());
			map.put("address",j.get("address").toString());
			map.put("tel",j.get("tel").toString());
			list.add(map);
		}
		System.out.printf("%d 페이지 완료\n",page);
		return list;
	}
	public List<Map<String, String>> kakaoCrawlFutMatch(String search, int page){
		final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36";
		final String kakaoKey = "KakaoAK 28d9076d78b899a3f85bb1c12320b0c3";
		String url = "http://dapi.kakao.com/v2/local/search/keyword.json?query="+search+"&page="+page;
		List<Map<String, String>> list = new ArrayList<>();
		JSONObject json = null;
		Map<String, String> map = null;
		try {
			Connection.Response html = Jsoup.connect(url)
			.method(Connection.Method.GET)
			.userAgent(USER_AGENT)
			.header("Authorization", kakaoKey)
			.ignoreContentType(true)
			.execute();
			json = new JSONObject(html.parse().select("body").text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONArray jsonArr = json.getJSONArray("documents");
		for(int i = 0; i < jsonArr.length()-1; i++) {
			map = new HashMap<>();
			JSONObject j = jsonArr.getJSONObject(i);
			map.put("name",j.get("place_name").toString());
			map.put("address",j.get("address_name").toString());
			map.put("tel",j.get("phone").toString());
			list.add(map);
		}
		System.out.printf("%s %d페이지 완료\n%s\n",search,page,jsonArr.length());
		return list;
	}
}
