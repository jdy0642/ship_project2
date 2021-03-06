package com.ship.web.lol;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.ship.web.proxy.CrawlProxy;
import com.ship.web.proxy.Proxy;
@Component
public class LolInit extends Proxy implements ApplicationRunner{
   private LolRepository lolRepository;
   @Autowired CrawlProxy crawler;
   @Autowired Proxy pxy;
   @Autowired
   public LolInit(LolRepository lolRepository) {
      this.lolRepository = lolRepository;
   }
   @Override
   public void run(ApplicationArguments args) throws Exception {
      long count = lolRepository.count();
      Lol lol = null;
      if(count == 0) {
         String[] img = {
               "ahri",
               "Ashe",
               "garen",
               "jarvaniv",
               "Jinx",
               "Leona",
               "Lux",
               "missfortune",
               "neeko",
               "sona",
               "Teemo",
               "xayah",
               "xinzhao",
               "Yasuo",
               "zoe",
         };
         String[] tiers= {
               "Iron",
               "Bronze",
               "Silver",
               "Gold",
               "Platinum",
               "Diamond",
               "Master",
               "Grandmaster",
               "Challenger"
         };
         String[] positions = {
               "top",
               "jungle",
               "mid", 
               "bot", 
               "supporter"
         }; 
         
         List<Map<String,String>> lolList = new ArrayList<>();
         List<Map<String,String>> lolList1 = new ArrayList<>();
         for(int i =1;i<5;i++) {
         lolList.addAll(crawler.loltitleCrawling(i));
         }
            for(int i =1;i<3;i++) {
               lolList1.addAll(crawler.lolidCrawling(i)); 
            }
            
         for(int i = 0; i < 80 ; i++) {
            lol = new Lol();
            lol.setTitle(lolList.get(i).get("title"));
            lol.setContents("안녕");
            lol.setRhost(lolList1.get(i).get("rhost"));
            lol.setRguest("");
            lol.setTier(tiers[pxy.random(0, 8)]);
            lol.setCrawltier(lolList1.get(i).get("crawltier"));
            lol.setCrawlrate(lolList1.get(i).get("crawlrate"));
            lol.setLolblack("");
            lol.setImgurl(img[pxy.random(0, 14)]);
            lol.setWtime(new Date());
            lol.setPosition(positions[pxy.random(0, 4)]);
            lolRepository.save(lol);
         }
         
         
      }
   }

}