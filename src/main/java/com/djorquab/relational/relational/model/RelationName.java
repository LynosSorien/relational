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

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RELATION_TYPES")
@Entity
public class RelationName implements AbstractEntity {
	private static final long serialVersionUID = 787398949438466090L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "registered_on")
	private Date createdOn;
}
