package com.cos.blog.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 200)
	private String content;  
	
	@ManyToOne    // Reply(This)가 Many,  Board가 One
	@JoinColumn(name = "boardId")
	private Board board;		
	
	@ManyToOne    // Reply(This)가 Many,   User가 One
	@JoinColumn(name = "userId")
	private User user;		
	
	@CreationTimestamp
	private Timestamp createDate;
}
