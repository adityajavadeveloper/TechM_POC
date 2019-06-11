package com.sales.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name="Customer_SOS")
@Data
public class CustomerSOS {

	@Id
    @GeneratedValue
    @Column(name="cust_id")
	@JsonProperty("id")
	private long custId;
	
	@Column(name="cust_first_name")
	@JsonProperty("firstName")
	private String firstName;
	
	@Column(name="cust_last_name")
	@JsonProperty("lastName")
	private String lastName;
	
	@Column(name="cust_email")
	@JsonProperty("email")
	private String email;
	
}
