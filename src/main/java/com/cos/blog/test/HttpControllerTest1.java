package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest1 {

	@GetMapping("/http/lombok")
	public String lombokTest()
	{
		//Member m = new Member(1, "ssar", "1234", "email");
		Member m = Member.builder().username("ssar").password("1234").email("email").build();
		System.out.println(m.getId());
		m.setId(5000);
		System.out.println(m.getId());
		return "lombok test 완료";
		
		
	}
	@GetMapping("/http/get")
	//public String getTest(@RequestParam int id, @RequestParam String username) {
	public String getTest(Member m) {	// ?id=1&username=ssar&password=1234&email=ssar@nate.com 
		return "get 요청 : " + m.toString();
	}
	
	@PostMapping("/http/post")
	//public String postTest(@RequestBody String text) {
	public String postTest(@RequestBody Member m) {
		return "post 요청 : " + m;
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청 : " + m;
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
