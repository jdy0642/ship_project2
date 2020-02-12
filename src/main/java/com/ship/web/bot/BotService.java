package com.ship.web.bot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.Morpheme;

@Service
public class BotService {
	final private List<String> time = Arrays.asList("년","월","일","시","분");
	final private List<String> location = Arrays.asList("풋살장","경기장","풋살");
	final private List<String> todo = Arrays.asList("해");
		
	public Map<?, ?> analyzerEojeol(String msg) {
		Map<String, Object> map = new HashMap<>();
		Analyzer.parseEojeolJava(msg).forEach(p->System.out.println(p));
		return map;
	}
	
	public List<Morpheme> analyzer(String msg) {
		List<Morpheme> list = new ArrayList<>();
		Analyzer.parseJava(msg).forEach(p->{
			list.add(p.morpheme());
//			map.put(String.format("%d_%s"
//				,p.beginOffset()
//				,p.morpheme().getSurface())
//				,p.morpheme().getFeature());
//			System.out.println(p.morpheme().getFeatureHead());
		});
		return list;
	}
	
	public Map<?, ?> distinction(List<Morpheme> analyzer) {
		Map<String, Object> map = new HashMap<>();
		List<String> timeStack = new ArrayList<>();
		List<String> locationStack = new ArrayList<>();
		List<String> todoStack = new ArrayList<>();
		List<String> arrTemp = new ArrayList<>();
		analyzer.forEach(i->{
			
			switch (i.getFeatureHead()) {
			case "SN": break;
			case "NNBC": {
				Morpheme sn = analyzer.get(analyzer.indexOf(i)-1);
				if(sn.getFeatureHead().equals("SN") && time.contains(i.getSurface())) {
					timeStack.add(sn.getSurface()+i.getSurface());
					}
//				arrTemp.add(i.getSurface());
//				if(time.contains(i.getSurface())) {
//					timeStack.addAll(arrTemp);
//					arrTemp.clear();
//				}
				}
				break;
			case "MAG": {
				if(i.getFeature().contains("시간부사")) {
					int temp = 0;
					switch (i.getSurface()) {
						case "오늘": temp = 0; break;
						case "내일": temp = 1; break;
						case "모레": temp = 2; break;
						case "어제": temp = -1; break;
						case "엊그저께": temp = -2; break;
						}
					timeStack.add(new SimpleDateFormat("yyyy년 MM월 dd일")
							.format(new Date().getTime()+(long)(temp * 1000 * 60 * 60 * 24 )));
					}
				}
				break;
			case "NNP":{
				arrTemp.add(i.getSurface());
				}
				break;
			case "NNG":{
				arrTemp.add(i.getSurface());
				if(location.contains(i.getSurface())) {
					locationStack.addAll(arrTemp);
					arrTemp.clear();
				}
				}
				break;
			case "VX+EC": {
				arrTemp.add(i.getSurface());
				if(todo.contains(i.getSurface())) {
					todoStack.addAll(arrTemp);
					arrTemp.clear();
				}
				}
				break;
			case "XSV+EC": {
				arrTemp.add(i.getSurface());
				if(todo.contains(i.getSurface())) {
					todoStack.addAll(arrTemp);
					arrTemp.clear();
				}
				}
				break;
			default:{
				arrTemp.add(i.getSurface());
				if(todo.contains(i.getSurface())) {
					todoStack.addAll(arrTemp);
					arrTemp.clear();
				}
				}
				break;
			}
		});
		map.put("timeStack", timeStack);
		map.put("locationStack", locationStack);
		map.put("todoStack", todoStack);
		map.put("NNG", arrTemp);
		return map;
	}
	
	public void setDicts(List<String> word) {
		Analyzer.setUserDict(word.iterator());
	}
}
