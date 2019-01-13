package com.hex.challenger.MonolithAnalyzerDemo.Controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hex.challenger.MonolithAnalyzerDemo.dao.PersonalityRequest;
import com.hex.challenger.MonolithAnalyzerDemo.services.PersonalityInsightService;
import com.hex.challenger.MonolithAnalyzerDemo.services.TwitterService;

@RestController
@RequestMapping("/api")
public class MonolithController {
	
	@CrossOrigin
	@GetMapping("/moodstatus/{name}")
	public String getTwitterIntegration(@PathVariable String name) throws JsonParseException, JsonMappingException, IOException {
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		PersonalityInsightService service = new PersonalityInsightService();
		TwitterService tweetSrvc = new TwitterService();
		System.out.println("Input Twitter User: " + name);

		ObjectMapper mapper2 = new ObjectMapper();
		String jsonInString = mapper2.writeValueAsString(tweetSrvc.getAllTweets(name));
		System.out.println("Twitter Text: " + jsonInString);
		
		PersonalityRequest obj = mapper.readValue(jsonInString, PersonalityRequest.class);
		
		String str = service.getInsights(obj.getUserMessages());
		return str;
	}


}
