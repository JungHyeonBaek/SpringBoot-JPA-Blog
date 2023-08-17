package com.cos.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired  // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	@PostMapping("/dummy/join")
	//public String join(@RequestBody("username") String u, String password, String email) {
	public String join( String username, String password, String email) {
		System.out.println("username : " + username);
		System.out.println("password : " + password);
		System.out.println("email : " + email);
		
		return "회원가입이 완료되었습니다.";
	}
	
	
	@PostMapping("/dummy/join2")
	public String join2( User user) {
		System.out.println("user : " + user);
		
		user.setRole(RoleType.USER);	// default값 설정
		userRepository.save(user);
		
		return "회원가입이 완료되었습니다2.";
	}
}
