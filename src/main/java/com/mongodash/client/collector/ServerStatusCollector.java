package com.mongodash.client.collector;

import com.mongodash.client.Command;
import com.mongodash.client.ServerStatus;
import com.mongodash.client.ServerStatusException;
import com.mongodash.client.api.ApiClient;
import com.mongodash.client.connector.Connector;
import com.mongodash.client.converter.ServerStatusConverter;
import com.mongodash.client.log.Log;
import com.mongodb.DBObject;

public class ServerStatusCollector extends DataCollector {

	private ApiClient apiClient;
	private Connector connector;
	private Command command;
	private ServerStatus previous = null;
	private ServerStatus current = null;
	
	public ServerStatusCollector(ApiClient apiClient, Connector connector, Command command) {
		this.apiClient = apiClient;
		this.connector = connector;	
		this.command = command;
	}

	@Override
	void collect() {
		DBObject result = connector.getData(command);
		try {
			current = ServerStatusConverter.fromTCPDBObject(result);
		} catch (ServerStatusException e) {
			Log.error(e.getMessage());
		}
	}

	@Override
	void post() {
		if(previous == null) {
			previous = current;
		}
		else {
			ServerStatus diff = ServerStatusConverter.getDiff(previous, current, 
					apiClient.getAgentConfig().getUpdateInterval());
			System.out.println(diff);
			DBObject diffObj = ServerStatusConverter.toDBObject(diff);
			apiClient.postServerStatus(diffObj);
			previous = current;
		}	
	}

	@Override
	int getSeconds() {
		return apiClient.getAgentConfig().getUpdateInterval();
	}

}
