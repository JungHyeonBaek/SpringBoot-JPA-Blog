package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@Getter
//@Setter
//@RequiredArgsConstructor  // final 로 정의된 필드를 받는 생성자
@Data  //Getter + Setter
//@AllArgsConstructor  // 모든 필드를 받는 생성자
@NoArgsConstructor
public class Member {
	private int id;
	private String username;
	private String password;
	private String email;
	

	 // Builder 패턴의 생성자 만들어 준다. 
	// 장점 : 파리미터 순서를 맞출 필요가 없다. 
	@Builder
	public Member(int id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}




	
	
}
