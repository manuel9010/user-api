package com.manudev90.userapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PHONE")
public class PhoneEntity {
	
	    @Id
	    @GeneratedValue(strategy=GenerationType.AUTO)
	    private Long id;

	    private String number;
	    
	    private String cityCode;
	    
	    private String countryCode;
	    
	    

		public PhoneEntity() {

		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getCityCode() {
			return cityCode;
		}

		public void setCityCode(String cityCode) {
			this.cityCode = cityCode;
		}

		public String getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(String countryCode) {
			this.countryCode = countryCode;
		}
	    
	    
	    
	    
}


