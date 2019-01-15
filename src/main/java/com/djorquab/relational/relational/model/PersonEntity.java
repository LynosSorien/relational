package com.djorquab.relational.relational.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "PEOPLE")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity implements AbstractEntity {
	private static final long serialVersionUID = 9184838280363354148L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "created_on")
	private Date createdOn;

}
