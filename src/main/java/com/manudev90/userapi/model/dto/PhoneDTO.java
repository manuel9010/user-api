package com.manudev90.userapi.model.dto;

public class PhoneDTO {
	
	private String number;
	private String citycode;
	private String contrycode;
	

	
	public PhoneDTO() {
	
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	public String getContrycode() {
		return contrycode;
	}
	public void setContrycode(String contrycode) {
		this.contrycode = contrycode;
	}

	
	

}

