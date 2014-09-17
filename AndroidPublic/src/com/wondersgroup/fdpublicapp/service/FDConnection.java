package com.wondersgroup.fdpublicapp.service;


import java.util.ArrayList;

/**
 * Connection Manager
 * 
 * @author chengshaohua
 */
public class FDConnection {
	
	public static final int MAX_CONNECTIONS = 5;

	private ArrayList<Runnable> connectionActive = new ArrayList<Runnable>();
	private ArrayList<Runnable> connectionQueue = new ArrayList<Runnable>();

	private static FDConnection connectionInstance;

	public static FDConnection getInstance() {
		if (connectionInstance == null)
			connectionInstance = new FDConnection();
		return connectionInstance;
	}

	public void push(Runnable runnable) {
		connectionQueue.add(runnable);
		if (connectionActive.size() < MAX_CONNECTIONS)
			startNext();
	}

	private void startNext() {
		if (!connectionQueue.isEmpty()) {
			Runnable next = connectionQueue.get(0);
			connectionQueue.remove(0);
			connectionActive.add(next);

			Thread thread = new Thread(next);
			thread.start();
		}
	}

	public void didComplete(Runnable runnable) {
		connectionActive.remove(runnable);
		runnable = null;
		startNext();
	}

}
