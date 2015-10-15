package com.mongodash.client.connector;

import java.net.UnknownHostException;

import com.mongodash.client.AgentConfig;
import com.mongodash.client.Command;
import com.mongodash.client.log.Log;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TcpConnector implements Connector {

	private MongoClient mongoClient;
	private DB mongoDb;
	
	public TcpConnector(AgentConfig agentConfig) {
		try {
			mongoClient = new MongoClient(agentConfig.getMongoHost(), agentConfig.getMongoPort());
			mongoDb = mongoClient.getDB("admin");
			Log.info("Connected to " + agentConfig.getMongoHost() + " on port " + agentConfig.getMongoPort());
		} catch (UnknownHostException e) {
			Log.error("Error while creating connection with mongodb");
			Log.error("Error message: " + e.getMessage());
		}
	}	
	
	public DBObject getData(Command command) {
		return mongoDb.command(command.getCommand());
	}

}
