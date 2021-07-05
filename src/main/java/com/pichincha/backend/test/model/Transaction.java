package com.pichincha.backend.test.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name="type")
	private String type;
	
	@Column(name = "creationdate")
	private LocalDateTime creationDate;
	
	@Column(name = "id_account")
	private Long idAccount;
	
	
}
