package com.mongodash.client;

public class AgentConfig {

	public enum TYPE { http, tcp };
	
	private String mongoDashHost;
	private Integer updateInterval;
	private TYPE agentType;
	private String agentKey;
	private String mongoHost;
	private Integer mongoPort;
	private String mongoUser;
	private String mongoPass;

	public String getMongoDashHost() {
		return mongoDashHost;
	}

	public void setMongoDashHost(String mongoDashHost) {
		this.mongoDashHost = mongoDashHost;
	}

	public Integer getUpdateInterval() {
		return updateInterval;
	}

	public void setUpdateInterval(Integer updateInterval) {
		this.updateInterval = updateInterval;
	}

	public TYPE getAgentType() {
		return agentType;
	}

	public void setAgentType(TYPE agentType) {
		this.agentType = agentType;
	}

	public String getMongoHost() {
		return mongoHost;
	}

	public void setMongoHost(String mongoHost) {
		this.mongoHost = mongoHost;
	}

	public Integer getMongoPort() {
		return mongoPort;
	}

	public void setMongoPort(Integer mongoPort) {
		this.mongoPort = mongoPort;
	}

	public String getMongoUser() {
		return mongoUser;
	}

	public void setMongoUser(String mongoUser) {
		this.mongoUser = mongoUser;
	}

	public String getMongoPass() {
		return mongoPass;
	}

	public void setMongoPass(String mongoPass) {
		this.mongoPass = mongoPass;
	}

	public String getAgentKey() {
		return agentKey;
	}

	public void setAgentKey(String agentKey) {
		this.agentKey = agentKey;
	}

}
