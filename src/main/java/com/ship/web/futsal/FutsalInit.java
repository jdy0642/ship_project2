package com.ship.web.futsal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.Proxy;
import com.ship.web.proxy.Trunk;

@Component
public class FutsalInit extends Proxy implements ApplicationRunner{
	private FutsalMatchRepository futsalMatchRepository;
	@Autowired CrawlProxy crawler;
	
	@Autowired
	public FutsalInit(FutsalMatchRepository futsalMatchRepository) {
		this.futsalMatchRepository = futsalMatchRepository;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = futsalMatchRepository.count();
		FutsalMatch match = null;
		List<Map<String, String>> stadiumList = new ArrayList<>();
		List<FutsalMatch> matchList = new ArrayList<FutsalMatch>();
		String[] locate = {"서울","경기","인천","강원","세종","충청","대전","대구","전라","경상","부산","광주","울산"};
		String[] img = {"11,12,13","21,22,23","31,32,33","41,42,43","51,52,53","61,62,63",
				"71,72,73","81,82,83","91,92,93","a1,a2,a3","b1,b2,b3","c1,c2,c3"};
		if(count < 50) {
			for(int i = 0; i<=12; i++) {
				for(int j = 1; j<=3; j++) {
					stadiumList.addAll(crawler.kakaoCrawlFutMatch(locate[i]+" 풋살",j));
				}
			}
			for(int i = 0; i<=5000; i++) {
				int ranIndex = random(1, stadiumList.size()-1);
				match = new FutsalMatch();
				match.setTime(System.currentTimeMillis()-(random(1,480)-240)*1000*3600);
				match.setStadiumname(stadiumList.get(ranIndex).get("name"));
				match.setStadiumaddr(stadiumList.get(ranIndex).get("address"));
				match.setStadiumtel(stadiumList.get(ranIndex).get("tel"));
				match.setStadiumimg(img[ranIndex%12]);
				match.setNum(random(4, 6));
				match.setGender(Arrays.asList("male","female","male").get(random(1,2)));
				match.setDifficulty(random(1, 3));
				match.setShoes("shoes0");
				match.setStadiumfacility(String.format("size1,shower%s,park%s,shoes%s,wear%s"
						,random(0,1),random(0,1),random(0,1),random(0,1)));
				match.setRemain(random(1, 14));
				match.setAdminname("펭수");
				matchList.add(match);
			}
			futsalMatchRepository.saveAll(matchList);
		}
	}
}
