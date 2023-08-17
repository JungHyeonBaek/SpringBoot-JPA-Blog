package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome");
		
		// 파일 리턴의 기본 경로 : src/main/resources/static
		// 그래서 파일을 리턴할 때는 "/"로 시작하도록 파일 경로를 적어줘야 한다.
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg() {
		return "/a.png";	// 브라우저에 이미지가 표시 됨
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		//return "/test.jsp";	// 에러 발생 => .jsp 파일은 정적 파일이 아니기 때문에 브라우저에 내려주지 않음
		
		// application.yml 에 jsp 경로 설정 (spring -> mvc -> view)
		//     prefix: /WEB-INF/views/
		//    suffix: .jsp
		return "test";  // => /WEB-INF/views/test.jsp
	}
}
