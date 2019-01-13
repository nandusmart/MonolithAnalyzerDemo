package com.hex.challenger.MonolithAnalyzerDemo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hex.challenger.MonolithAnalyzerDemo.dao.Tweet;

import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

@Component
public class TwitterService {
	
	ConfigurationBuilder configBuilder = new ConfigurationBuilder().setDebugEnabled(true)
	  .setOAuthConsumerKey("qWHs2ZMrs4CGG906CfbyImZmF")
	  .setOAuthConsumerSecret("3eTXTXZC6fpCLJzpGubWWcY056LUk72Jxz7c5GNuYkS1J3vCpY")
	  .setOAuthAccessToken("1062167800373014530-32zo3GTzwLj7AjVXaWRYA6ofP9vjiz")
	  .setOAuthAccessTokenSecret("oAGnvnRBcwKHkHrEsKUQ9aYonDpJfHhazJX0Cv0pYJlm9");
	
	TwitterFactory tf = new TwitterFactory(configBuilder.build());
	Twitter twitter = tf.getInstance();
	
	
	
	
	
	public Tweet getAllTweets(String username)
	{
		String tweetMessage=new String();
		Tweet tweets=new Tweet();
		try
		{
			Query query = new Query("from:"+username);
			query.setCount(100);
			QueryResult result;
			//do {
                result = twitter.search(query);
                List<Status> pulledTweets = result.getTweets();
                for (Status tweet : pulledTweets) {
                	tweetMessage=tweetMessage+" "+tweet.getText().replaceAll("[\\S]+://[\\S]+", "");
                	
                }
            //} while ((query = result.nextQuery()) != null);
			
		 tweetMessage=tweetMessage.replaceAll("[^A-Za-z0-9 ]", " ");
		 tweetMessage=tweetMessage.replaceAll("( )+", " ");
			tweets.setUserId(username);
			tweets.setUserMessages(tweetMessage);
			
		}
		catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        } 
		
		return tweets;
	}
	
	
	
}
