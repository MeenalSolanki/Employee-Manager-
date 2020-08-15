package com.mindBrowser.manageremployee.service;

import com.mindBrowser.manageremployee.entity.Manager;

public class StoreCurrentManager {

	static Manager manager;
	
	private StoreCurrentManager(Manager manager1){
		manager = manager1;
	}
	
	public static Manager getManager() {
		return manager;
	}
	public static void setManager(Manager manager1) {
		manager = manager1;
	}	
}
