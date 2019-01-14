package com.djorquab.relational.relational.model;

import java.io.Serializable;
import java.util.Date;

public interface AbstractEntity extends Serializable{
	Date getCreatedOn();
	void setCreatedOn(Date createdOn);
	Date getLastModified();
	void setLastModified(Date lastModified);
}
