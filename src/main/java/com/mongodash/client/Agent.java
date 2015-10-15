package com.mongodash.client;

import com.mongodash.client.api.ApiClient;
import com.mongodash.client.collector.BuildInfoCollector;
import com.mongodash.client.collector.DatabasesCollector;
import com.mongodash.client.collector.HostInfoCollector;
import com.mongodash.client.collector.ServerInfoCollector;
import com.mongodash.client.collector.ServerStatusCollector;
import com.mongodash.client.connector.Connector;
import com.mongodash.client.connector.HttpConnector;
import com.mongodash.client.connector.TcpConnector;
import com.mongodash.client.log.Log;

public class Agent {
	
	private AgentConfig agentConfig;
	
	//Collectors
	private BuildInfoCollector buildInfoCollector;
	private DatabasesCollector databasesCollector;
	private HostInfoCollector hostInfoCollector;
	private ServerInfoCollector serverInfoCollector;
	private ServerStatusCollector serverStatusCollector;

	public Agent(AgentConfig agentConfig) {
		this.agentConfig = agentConfig;
	}
	
	public void start() {
		
		Connector agentConnector = getConnector();
		
		if(!testAgentKey()) {
			Log.error("Invalid agent key");
			Log.abort("Aborting...");
		}
		
		ApiClient apiClient = new ApiClient(agentConfig);
		
		buildInfoCollector = new BuildInfoCollector(apiClient, agentConnector, Command.BUILD_INFO);
		databasesCollector = new DatabasesCollector(apiClient, agentConnector, Command.DATABASES);
		hostInfoCollector = new HostInfoCollector(apiClient, agentConnector, Command.HOST_INFO);
		serverInfoCollector = new ServerInfoCollector(apiClient, agentConnector, Command.SERVER_STATUS);
		serverStatusCollector = new ServerStatusCollector(apiClient, agentConnector, Command.SERVER_STATUS);
		
		buildInfoCollector.start();
		databasesCollector.start();
		hostInfoCollector.start();
		serverInfoCollector.start();
		serverStatusCollector.start();
		
	}
	
	public void shutdown() {
		Log.info( "Stopping data collectors" );
		buildInfoCollector.shutdown();
		databasesCollector.shutdown();
		hostInfoCollector.shutdown();
		serverInfoCollector.shutdown();
		serverStatusCollector.shutdown();
		Log.info( "All data collectors stopped" );
	}
	
	private Connector getConnector() {
		
		Connector connector;
		
		if(AgentConfig.TYPE.tcp == agentConfig.getAgentType()) {
			connector = new TcpConnector(agentConfig);
		}
		else {
			connector = new HttpConnector(agentConfig);
		}
		
		return connector;
		
	}
	
	private boolean testAgentKey() {
		return true;
	}
	
	
}
