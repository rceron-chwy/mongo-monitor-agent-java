package com.mongodash.client.collector;

public abstract class DataCollector extends Thread {

	private boolean active = true;
	
	abstract void collect();
	
	abstract void post();
	
	abstract int getSeconds();
	
	@Override
	public void run() {
		while(true && active) {
			collect();
			post();
			sleep(getSeconds(), active);
		}
	}
	
	public void shutdown() {
		active = false;
	}
	
	private void sleep(int interval, boolean isActive) {
		for (int i = 0; i < (interval * 2) && isActive; i++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
	}	
	
}
