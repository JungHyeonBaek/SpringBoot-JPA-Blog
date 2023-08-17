package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //  Getter, Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터
	private String content;  // 섬머노트 라이브러리 사용 예정 => html 로 작성됨
	
	@ColumnDefault("0") 
	private int count; // 조회수
	
	@ManyToOne    // Board(This)가 Many,   User가 One   
	                            // @ManyToOne((fetch = FetchType.EAGER)가 기본 fetch 전략   (가져온다)
	@JoinColumn(name = "userId") // 이런 이름으로 컬럼을 만들어 FK 컬럼 생성
	//private int userId; // 작성자    // DB는 KF 를 사용해야 하지만
	private User user;		// JPA(ORM)은 Object를 저장할 수 있기 때문에 객체로 FK를 잡는다
	

	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER) // 연관관계의 주인이 아니다. (FK 컬럼을 만들지 않는다)
																     // mappedBy값은 Reply 클래스의 Board 필드 이름을 적어준다
																     // 기본 페치 전략이 , fetch = FetchType.LAZY 임 (안가져온다)
	
	private List<Reply> reply;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
