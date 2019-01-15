package com.djorquab.relational.relational.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "COUNTRIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountryEntity implements AbstractEntity {
	private static final long serialVersionUID = 7336163021410612932L;

	@Id
	@Column(name = "code")
	private String code;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "created_on")
	private Date createdOn;
}
