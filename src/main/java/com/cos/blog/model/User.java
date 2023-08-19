package com.cos.blog.model;

import java.sql.Timestamp;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data //  Getter, Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity // User 클래스가 MySQL에 테이블이 생성 된다.
//@DynamicInsert   // 값이 null인 컬럼은 insert 문에서 제외시켜준다 => ColumnDefault 값을 의미있게 사용할 수 있음
public class User {

	@Id // Primary Key
	@GeneratedValue(strategy= GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다 (Oracle=Sequence, MySQL=AutoIncrement)
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 30, unique = true)
	private String username;  // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(nullable = false, length = 50)
	private String email;
	
//	@ColumnDefault("'user'")  // 문자니까 작은 따음표 들어 있음.  이 값은 Table에 defaultValue로 정의되며 Java 에서 자동으로 넣어주진 않음
//	                                                // 하여 이 값을 셋팅하지 않고 Insert하면 role에 명시적으로 null을 넣게 되므로 원하는 결과가 나오지 않음 
//	                                               // @DynamicInsert를 붙이면 null 값 무시 가능 
//                                               // 하지만 기교 경진대회도 아니고 @를 너무 많이 붙이는 듯 하니 직접 구현하는게 좋다는... 
//	//private String role; // Enum을 사용하는 것이 좋다. (도메인을 정할 수 있으니까) 
	@Enumerated(EnumType.STRING)  //이렇게 하면 enum -> String 으로 변환해서 Insert 해줌
	private RoleType role;
	
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
