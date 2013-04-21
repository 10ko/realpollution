package com.thepollutions.realpollution.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.SortDirection;


@Service  
public class ApiServiceImpl implements ApiService {  

	@Override  
	public List<DataObj> getAllDataObj(String deviceId) {  
		ArrayList<DataObj> list = new ArrayList<DataObj>();  
		DatastoreService datastore = DatastoreServiceFactory  
				.getDatastoreService();

		// The Query interface assembles a query  
		Query q = new Query("DataObj");  
		q.addFilter("deviceId", Query.FilterOperator.EQUAL, deviceId);  
		//q.addSort("score", SortDirection.DESCENDING);  

		// PreparedQuery contains the methods for fetching query results  
		// from the datastore  
		PreparedQuery pq = datastore.prepare(q);  

		for (Entity result : pq.asIterable()) {   
			double longCord = (double) result.getProperty("longCord");  
			double latCord = (double) result.getProperty("latCord");  
			double value = (double) result.getProperty("value");
			long time = (Long) result.getProperty("time");
			
			list.add(new DataObj(deviceId, longCord, latCord, value, time));  
		}  

		return list;  
	}

	@Override
	public List<String> getAllApplications() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDataObj(DataObj dataObj) {

		Entity item = new Entity("DataObj");
		item.setProperty("deviceId", dataObj.getDeviceId());
		item.setProperty("longCord", dataObj.getLongCord());
		item.setProperty("latCord", dataObj.getLatCord());
		item.setProperty("value", dataObj.getValue());
		item.setProperty("time", dataObj.getTime());


		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		datastore.put(item);

	} 



}
