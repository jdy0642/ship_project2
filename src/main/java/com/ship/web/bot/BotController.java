package com.ship.web.bot;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/bot")
@RestController
public class BotController {
	@PostMapping("/{msg}")
	public boolean msg() {
		return false;
	}
}
