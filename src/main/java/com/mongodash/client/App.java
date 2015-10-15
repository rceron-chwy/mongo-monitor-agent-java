package com.mongodash.client;

import java.io.File;
import java.io.FileReader;
import java.util.Map.Entry;
import java.util.Properties;

import com.mongodash.client.AgentConfig.TYPE;
import com.mongodash.client.log.Log;

public class App {

	public static void main(String[] args) {

		AgentConfig agentConfig = parseProperties(args);

		if (agentConfig == null) {
			Log.abort("Aborting...");
		}
		
		if(testConnection(agentConfig)) {
			final Agent agent = new Agent(agentConfig);
			Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					Log.info("Shutdown iniciated");
					if (agent != null) {
						agent.shutdown();
					}
					Log.info("Shutdown completed");
				}
			});
			Log.info("Initiating MongoDASH Agent...");
			agent.start();
		}
	}

	public static AgentConfig parseProperties(String args[]) {

		Log.info("Loading configuration file");

		if (System.getProperty("agent.properties") != null) {
			String propertiesFile = System.getProperty("agent.properties");
			Log.info("Agent configuration file [" + propertiesFile + "]");
			File propFile = new File(propertiesFile);
			if (propFile.exists()) {

				try {
					FileReader reader = new FileReader(propertiesFile);
					Properties prop = new Properties();

					prop.load(reader);
					reader.close();
					for (Entry<Object, Object> e : prop.entrySet()) {
						Log.info("Property [" + e.getKey() + "], value ["
								+ e.getValue() + "]");
					}

					AgentConfig agentConfig = new AgentConfig();
					if (setupConfig(agentConfig, prop)) {
						return agentConfig;
					}

				} catch (Exception e) {
					Log.error("Error while loading configuration file");
					Log.error("Error message: " + e.getMessage());
					Log.abort("Aborting...");
				}

			} else {
				Log.error("Agent configuration file not found");
				Log.abort("Aborting...");
			}

		} else {

			Log.error("Missing configuration file parameter");
			Log.error("Start the agent with -Dagent.properties=CONFIG_FILE");
			Log.abort("Aborting...");

		}

		return null;

	}

	public static boolean setupConfig(AgentConfig agentConfig, Properties prop) {

		boolean result = true;

		if (prop.containsKey(AgentProperty.agentKey)) {
			agentConfig.setAgentKey(prop.getProperty(AgentProperty.agentKey));
		} else {
			Log.error("Agent KEY not found, make sure you have "
					+ AgentProperty.agentKey + " on your configuration file");
			result = false;
		}

		if (prop.containsKey(AgentProperty.mongoDashHost)) {
			agentConfig.setMongoDashHost(prop
					.getProperty(AgentProperty.mongoDashHost));
		} else {
			Log.error("MongoDASH host not found, make sure you have "
					+ AgentProperty.mongoDashHost + " on your configuration file");
			result = false;
		}

		if (prop.containsKey(AgentProperty.updateInterval)) {
			agentConfig.setUpdateInterval(Integer.parseInt(prop
					.getProperty(AgentProperty.updateInterval)));
		} else {
			Log.info("Property " + AgentProperty.updateInterval
					+ ", not found, setting updating interval to 5 seconds");
			agentConfig.setUpdateInterval(5);
		}

		if (prop.containsKey(AgentProperty.agentType)) {
			agentConfig.setAgentType(TYPE.valueOf(prop
					.getProperty(AgentProperty.agentType)));
		} else {
			Log.info("Property " + AgentProperty.agentType
					+ ", not found, setting agent type to tcp");			
			agentConfig.setAgentType(TYPE.tcp);
		}

		if (prop.containsKey(AgentProperty.mongoHost)) {
			agentConfig.setMongoHost(prop.getProperty(AgentProperty.mongoHost));
		} else {
			Log.info("Property " + AgentProperty.mongoHost
					+ ", not found, setting MongoDB host to 127.0.0.1");
			agentConfig.setMongoHost("127.0.0.1");
		}

		if (prop.containsKey(AgentProperty.mongoPort)) {
			agentConfig.setMongoPort(Integer.parseInt(prop
					.getProperty(AgentProperty.mongoPort)));
		} else {
			Log.info("Property " + AgentProperty.mongoPort
					+ ", not found, setting MongoDB port to 27012");
			agentConfig.setMongoPort(27107);
		}

		if (prop.containsKey(AgentProperty.mongoUser)) {
			agentConfig.setMongoUser(prop.getProperty(AgentProperty.mongoUser));
		}

		if (prop.containsKey(AgentProperty.mongoPass)) {
			agentConfig.setMongoPass(prop.getProperty(AgentProperty.mongoPass));
		}

		return result;

	}

	public static boolean testConnection(AgentConfig agentConfig) {
		Log.info("Validating agent configuration");
		if (AgentConfig.TYPE.tcp.equals(agentConfig.getAgentType())) {
			// connector = new TcpConnector(agentConfig);
		} else {
			// connector = new HttpConnector(agentConfig);
		}

		//test if mongodash is reachable
		
		return true;

	}

}
