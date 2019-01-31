package com.djorquab.relational.relational.model;

import java.util.Date;

import javax.persistence.*;

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
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "surname")
	private String surname;
	
	@Column(name = "description")
	private String description;

	@Column(name = "image")
	private String image;
	
	@Column(name = "birthday")
	@Temporal(TemporalType.DATE)
	private Date birthday;
	
	@Column(name = "deathdate")
	@Temporal(TemporalType.DATE)
	private Date deathdate;
	
	@Column(name = "last_modified", nullable = false)
	private Date lastModified;
	
	@Column(name = "created_on", nullable = false)
	private Date createdOn;

}
