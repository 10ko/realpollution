package com.thepollutions.realpollution.api;

import java.util.List;

public interface ApiService {

	List<DataObj> getAllDataObj(String deviceId);

	List<String> getAllApplications();

	void addDataObj(DataObj dataObj);


}
