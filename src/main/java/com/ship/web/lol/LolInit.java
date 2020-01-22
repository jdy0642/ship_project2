package com.ship.web.lol;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;

import lombok.Setter;
@Component
public class LolInit extends Proxy implements ApplicationRunner{
	private LolRepository lolRepository;
	
	@Autowired
	public LolInit(LolRepository lolRepository) {
		this.lolRepository = lolRepository;
	}
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = lolRepository.count();
		if(count == 0) {
			Lol lol = null;
			String[] img = {
					"https://w.namu.la/s/65e9c3777f6ccdb9e462635fd238f9bc30f2248bf4bff65178a31d8114f8f841fccdff26d0ef4b9a9f89698ab098c73fb54fb505b967ff6519dba358eabfdac15953d557f2dad09497236c50aaaf9f913b5feb26ef546432b8c3691bee906d5c6847f42160a7f7e9f4a7c3b621b88717",
					"https://w.namu.la/s/b4fe89a2dc24d174ebba268d870c8672b0c40faaf4df07d0b36cc5ee8f5f40efe96007b2d3521aa6ca5805f4dce381f7914261627151c3627448d147982b3a87770cbdd59d21e76d2f64d040fcfe036f213102d46ef94824a269663dac73f262",
					"https://w.namu.la/s/0f03c257f252cb25791122b542f3c0b47d0d9c4631bc80d927f39836fefbc5f55247c28bda1ac910e6ffc2f278e3f198a1d940817bb752b1b80610703bb9511d250993cd3c998cb1699dab4535214332ac2cafb05f787bacf6d111bb8a94bb7cb5661acb2d409bd69cfa2e769b9d67ab",
					"https://ww.namu.la/s/8b0a7e865d8841c425fc4f27b20c48281920bc574cffd4b9d014b867fdf443d391b841f307ca25fd2cf012758ef0ad9e312e3f12c929ba509a088075dcd0394ca839bf1a70fe53b0ff03e64eb409a1926a6710e30c532fc0e6c52f5a6e4909e5089bd055350211482a80fea3bdf8c6a7",
					"https://ww.namu.la/s/6b4b67c5190df5f22ca4d4124e40901f80efa0e6bf5b9755c1daf5a6772a4664eed813cc049159c991c8981f46e728e5a2709f0fcaf05f1ee48f029d5d24eef98dcabc26a4f169a96b5c234b89c94fcabc634e7c2d853e91590f18235966cfda7763743d216e844a976501fed23e5daf"
			};
			int a = (int) Math.random()*4+1;
			
			String[][] mtx = {
					{"미드구함","bronze","빅토르/아리","","","",img[a]},
					
			};
			for(String[] arr : mtx) {
				lol = new Lol();
				lol.setTitle(arr[0]);
				lol.setTier(arr[1]);
				lol.setContents(arr[2]);
				lol.setLolblack(arr[3]);
				lol.setRhost(arr[4]);
				lol.setRguest(arr[5]);
				lol.setImgurl(img[a]);
				lolRepository.save(lol);
			}
		}
	}

}
