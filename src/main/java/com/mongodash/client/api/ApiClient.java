package com.mongodash.client.api;

import com.mongodash.client.AgentConfig;
import com.mongodb.DBObject;

public class ApiClient {

	private AgentConfig agentConfig;
	
	public ApiClient(AgentConfig agentConfig) {
		this.agentConfig = agentConfig;
	}
	
	public void postServerStatus(DBObject serverStatus) {
		//System.out.println(serverStatus);
	}
	
	public void postHostInfo(DBObject hostInfo) {
		
	}
	
	public void postBuildInfo(DBObject buildInfo) {
		
	}
	
	public void postServerInfo(DBObject serverInfo) {
		
	}
	
	public void postDababases(DBObject databasesInfo) {
		
	}
	
	public boolean postPing() {
		
		return true;
		
	}
	
	public AgentConfig getAgentConfig() {
		return this.agentConfig;
	}
	
}
