package com.mongodash.client;

public enum Command {
	
	SERVER_STATUS("serverStatus"), BUILD_INFO("buildInfo"), HOST_INFO("hostInfo"), DATABASES("listDatabases");
	
	private String command;
	
	private Command(String command) {
		this.command = command;
	}
	
	public String getCommand() {
		return this.command;
	}

}
