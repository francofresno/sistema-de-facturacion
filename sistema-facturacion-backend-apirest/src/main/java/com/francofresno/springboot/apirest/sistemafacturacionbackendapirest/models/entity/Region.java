package com.francofresno.springboot.apirest.sistemafacturacionbackendapirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "regions")
public class Region implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "r_name")
	private String rName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRName() {
		return rName;
	}

	public void setRName(String rName) {
		this.rName = rName;
	}

	private static final long serialVersionUID = 1L;

}
