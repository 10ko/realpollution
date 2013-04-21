package com.thepollutions.realpollution.api;

public class DataObj {

	private String deviceId;
	private double longCord;
	private double latCord;
	private double value;
	private long time;
	
	
	
	public DataObj(String deviceId, double longCord, double latCord, double value,
			long time) {
		this.deviceId = deviceId;
		this.longCord = longCord;
		this.latCord = latCord;
		this.value = value;
		this.time = time;
		
		
	}



	public String getDeviceId() {
		return deviceId;
	}



	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}



	public double getLongCord() {
		return longCord;
	}



	public void setLongCord(double longCord) {
		this.longCord = longCord;
	}



	public double getLatCord() {
		return latCord;
	}



	public void setLatCord(double latCord) {
		this.latCord = latCord;
	}



	public double getValue() {
		return value;
	}



	public void setValue(double value) {
		this.value = value;
	}



	public long getTime() {
		return time;
	}



	public void setTime(long time) {
		this.time = time;
	}



	@Override
	public String toString() {
		return "DataObj [deviceId=" + deviceId + ", longCord=" + longCord
				+ ", latCord=" + latCord + ", value=" + value + ", time="
				+ time + "]";
	}



	public String getJson() {
		return "\t\t{ \"deviceId\": \"" + getDeviceId() + "\", \"longCord\": \"" + getLongCord() + "\", \"latCord\": \"" + getLatCord() + "\", \"value\": \"" + getValue() + "\", \"time\": \"" + getTime() + "\"}";
	}
	
	
	

}
