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
@Table(name = "ROLES")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoleEntity implements AbstractEntity {
	private static final long serialVersionUID = -7212519840965654170L;

	@Id
	@Column(name = "role", unique = true, nullable = false)
	private String role;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "created_on")
	private Date createdOn;
}
