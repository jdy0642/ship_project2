package com.ship.web.lol;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.Proxy;

import lombok.Setter;
@Component
public class LolInit extends Proxy implements ApplicationRunner{
	private LolRepository lolRepository;
	@Autowired CrawlProxy crawler;
	@Autowired Proxy pxy;
	@Autowired
	public LolInit(LolRepository lolRepository) {
		this.lolRepository = lolRepository;
	}
//	@Override
//	public void run(ApplicationArguments args) throws Exception {
//		// TODO Auto-generated method stub
//		long count = lolRepository.count();
//		for(int i = 1; i<=3; i++) {
//			stadiumList.addAll(crawler.lolCrawling(i));
//		}
//		
//	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = lolRepository.count();
		Lol lol = null;
		if(count == 0) {
			String[] img = {
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Leona_Splash_11.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/rengar_splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/neeko_splash_2.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Lux_Splash_8.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/garen_splash_12.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Galio_Splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/gangplank_splash_8.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/graves_splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Gnar_splash_5.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/nami_splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Nasus_Splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/nunu_splash_3.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Nidalee_Splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/darius_splash_9.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/diana_splash_4.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/rakan_splash_5.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Lulu_Splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/LeeSin_Splash_11.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Riven_Splash_11.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/lissandra_splash_4.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/MasterYi_Splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Maokai_Splash_7.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/malphite_splash_8.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/missfortune_splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/missfortune_splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/varus_splash_7.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/vi_splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/vayne_splash_8.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/viktor_splash_3.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/poppy_splash_9.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/sylas_splash_2.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/senna_splash_0.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/sett_splash_1.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/sona_splash_7.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/soraka_splash_7.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/xinzhao_splash_14.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/syndra_splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/ahri_splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/amumu_splash_9.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/akali_splash_10.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Ashe_Splash_11.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/yasuo_splash_5.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/irelia_splash_6.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/ezreal_splash_5.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Janna_Splash_7.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/zoe_splash_3.jpg?v=20191212a",
					"http://static.inven.co.kr/image_2011/site_image/lol/dataninfo/icon/skinfull/Jinx_Splash_4.jpg?v=20191212a",
			};
			
			List<Map<String,String>> lolList = new ArrayList<>();
			List<Map<String,String>> lolList1 = new ArrayList<>();
				for(int i =1;i<=2;i++) {
					lolList.addAll(crawler.loltitleCrawling(i)); // title 40*2개 삽입
					lolList1.addAll(crawler.lolidCrawling(i)); // rhost, crawltier, crawlrate 40 * 2개  삽입
				}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
			for(int i = 0; i < 80 ; i++) {
				lol = new Lol();
				lol.setTitle(lolList.get(i).get("title"));
				lol.setContents("");
				lol.setRhost(lolList1.get(i).get("rhost"));
				lol.setRguest("");
				lol.setTier("GOLD");
				lol.setCrawltier(lolList1.get(i).get("crawltier"));
				lol.setCrawlrate(lolList1.get(i).get("crawlrate"));
				lol.setLolblack("");
				lol.setImgurl(img[pxy.random(1, 46)]);
				lol.setWtime(new Date());
				lolRepository.save(lol);
			}
			
			
		}
	}

}
