package com.mongodash.client.connector;

import java.net.URI;

import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.mongodash.client.AgentConfig;
import com.mongodash.client.Command;
import com.mongodash.client.http.HttpMonitorRequestHandler;
import com.mongodash.client.log.Log;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class HttpConnector implements Connector {

	private AgentConfig agentConfig;
	private HttpContext context;
	
	public HttpConnector(AgentConfig agentConfig) {
		this.agentConfig = agentConfig;
		this.context = new BasicHttpContext();
	}
	
	public DBObject getData(Command command) {
		
		DBObject result = null;
		
		try {
			URI uri = new URI("http", null, agentConfig.getMongoHost(), 
					agentConfig.getMongoPort(), "/serverStatus", "text=1", null);
			result = (DBObject) JSON.parse(HttpMonitorRequestHandler.getResponse(uri, context));
			return result;
		}
		catch(Exception e) {
			Log.error(e.getMessage());
		}
		
		return result;
	}

}
