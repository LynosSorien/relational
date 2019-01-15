package com.djorquab.relational.relational.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.djorquab.relational.relational.commons.RelationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "PEOPLE_RELATIONS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeopleRelationEntity implements AbstractEntity {
	private static final long serialVersionUID = -7177224787007184442L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "person_from")
	private PersonEntity personFrom;
	
	@ManyToOne
	@JoinColumn(name = "person_to")
	private PersonEntity personTo;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	private RelationType type;
	
	@ManyToOne
	@JoinColumn(name = "relation_name")
	private RelationName relationName;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@Column(name = "created_on")
	private Date createdOn;
}
