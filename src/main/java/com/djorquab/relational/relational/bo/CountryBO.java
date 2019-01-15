package com.djorquab.relational.relational.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryBO implements Serializable {
	private static final long serialVersionUID = -4579697928281817873L;
	
	private String code;
	private Date lastModified;
	private Date createdOn;
	
	public void setCountry(String country) {
		
	}
	
	public String getCountry() {
		if (code != null) {
			return new Locale("", code).getDisplayCountry();
		}
		return null;
	}
}
