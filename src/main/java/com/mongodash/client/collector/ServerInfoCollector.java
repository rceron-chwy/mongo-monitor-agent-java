package com.mongodash.client.collector;

import com.mongodash.client.Command;
import com.mongodash.client.api.ApiClient;
import com.mongodash.client.connector.Connector;

public class ServerInfoCollector extends DataCollector {
	
	private ApiClient apiClient;
	private Connector connector;
	private Command command;
	
	public ServerInfoCollector(ApiClient apiClient, Connector connector, Command command) {
		this.apiClient = apiClient;
		this.connector = connector;	
		this.command = command;
	}

	@Override
	void collect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	void post() {
		// TODO Auto-generated method stub
		
	}

	@Override
	int getSeconds() {
		return 3600; //86400
	}

}
