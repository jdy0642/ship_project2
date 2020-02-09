package com.ship.web.bot;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.util.Constants;

@RequestMapping("/bot")
@RestController
@CrossOrigin(origins = Constants.LOCAL)
public class BotController {
	@Autowired Bot bot;
	
	@GetMapping("/{msg}")
	public Bot msg(@PathVariable String msg) {
		bot.setMsg(msg);
		bot.exec();
		return bot;
	}
}
