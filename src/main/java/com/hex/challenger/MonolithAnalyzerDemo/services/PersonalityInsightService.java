package com.hex.challenger.MonolithAnalyzerDemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hex.challenger.MonolithAnalyzerDemo.dao.Tweet;
import com.ibm.watson.developer_cloud.personality_insights.v3.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Content;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.Profile;
import com.ibm.watson.developer_cloud.personality_insights.v3.model.ProfileOptions;
import com.ibm.watson.developer_cloud.service.security.IamOptions;
import com.ibm.watson.developer_cloud.util.GsonSingleton;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.TwitterException;

@Service
public class PersonalityInsightService {
	public String getInsights(String message) {
		IamOptions options = new IamOptions.Builder()
			    .apiKey("KNQUVkO1Bm_x5qUtFBH0zdMOt3iH07JpbNhGz2NAa2r2")
			    .build();

			PersonalityInsights personalityInsights = new PersonalityInsights("2017-10-13", options);

			personalityInsights.setEndPoint("https://gateway.watsonplatform.net/personality-insights/api");
			
			//Content content = new Content();
			
			ProfileOptions profileOptions = new ProfileOptions.Builder()
				    .text(message)
				    .consumptionPreferences(true)
				    .rawScores(true)
				    .build();

				  Profile profile = 
				    personalityInsights.profile(profileOptions).execute();
				  //System.out.println(profile);
		return profile.toString();
	}
	
}
