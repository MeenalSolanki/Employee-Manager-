package com.mindBrowser.manageremployee.service;

import com.mindBrowser.manageremployee.entity.Manager;

public class StoreCurrentManager {

	static Manager manager;
	
	public static Manager getManager() {
		return manager;
	}
	public static void setManager(Manager manager1) {
		manager = manager1;
	}	

}
