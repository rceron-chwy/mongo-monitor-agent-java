package com.mongodash.client.connector;

import com.mongodash.client.Command;
import com.mongodb.DBObject;

public interface Connector {
	
	DBObject getData(Command command);
	
}
