package com.ship.web.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ship.web.futsal.FutsalMatchRepository;
import com.ship.web.futsal.FutsalMatchService;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bitbucket.eunjeon.seunjeon.Morpheme;

@Component
@Getter
@Setter
@ToString
public class Bot extends BotService{
	private Map<?, ?> result;
	private List<Morpheme> analyzer; 
	private String msg;
		
	public Bot() {
		setDicts(Arrays.asList("풋살장,-200"));
	}

	public void exec() {
		analyzer = analyzer(msg);
		result = distinction(analyzer);
	}
}
