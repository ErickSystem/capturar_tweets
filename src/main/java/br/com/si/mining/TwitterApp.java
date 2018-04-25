package br.com.si.mining;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;

import br.com.si.variable.CredencialTwitter;

import java.net.UnknownHostException;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterApp {
	
	private ConfigurationBuilder cb;
	private DB banco;
	private DBCollection collection;
	
	public void capturaTweets() throws InterruptedException{
		
		TwitterStream twitterStream = 
				new TwitterStreamFactory(cb.build()).getInstance();
		StatusListener listener = new StatusListener() {
			public void onStatus(Status status) {
				// TODO Auto-generated method stub
				BasicDBObject obj = new BasicDBObject();
				obj.put("tweet_ID", status.getId());
				obj.put("usuario", status.getUser().getScreenName());
				obj.put("tweet", status.getText());
				
				try {
					collection.insert(obj);
				}catch (Exception e) {
					System.out.println("Erro de conexão: " 
							+ e.getMessage());
				}
				
			}

			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub
				
			}

			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub
				
			}

			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub
				
			}

		};
		
		String palavras[] = {"BigData"};
		FilterQuery fq = new FilterQuery();
		fq.track(palavras);
		twitterStream.addListener(listener);
		twitterStream.filter(fq);
	}	
	
	public void configuraCredenciais() {
		
		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(CredencialTwitter.API_KEY);
		cb.setOAuthConsumerSecret(CredencialTwitter.API_SECRET);
		cb.setOAuthAccessToken(CredencialTwitter.API_TOKEN);
		cb.setOAuthAccessTokenSecret(CredencialTwitter.API_TOKEN_SECRET);
		
	}
	
	public void conectaMongoDB() {
		
		try {
			Mongo mongoCli;

			mongoCli = new MongoClient("127.0.0.1");
			banco = mongoCli.getDB("twDB");
			collection = banco.getCollection("tweets");
			BasicDBObject index = new BasicDBObject("tweet_ID", 1);
			collection.ensureIndex(index,
					new BasicDBObject("unique", true));
			
		}catch (UnknownHostException ex) {
			System.out.println("MongoException :" + ex.getMessage());
		}
		
	}
	
}
