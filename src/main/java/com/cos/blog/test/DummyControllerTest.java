package com.cos.blog.test;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired  // 의존성 주입(DI)
	private UserRepository userRepository;
	
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		try {
			userRepository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다. 해당 id는 DB에 없습니다." + id;
		}
		
		return "삭제되었습니다." + id;
	}
	
	
	// email, password만 변경
	@Transactional  // 함수 종료시 자동 commit
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id: " + id);
		System.out.println("User : " + requestUser);


		// 1.
		// requestUser.setId(id)
		//userRepository.save(requestUser);	// 그냥 하면 username이 null이라 에러 발생


		// 2.
		// 원래 User를 조회한 후, 업데이트할 값을 변경하고 나서 다시 처리
//		User user = userRepository.findById(id).orElseThrow( () -> new NoSuchElementException("수정할 대상 없음"));
//		user.setPassword(requestUser.getPassword());
//		user.setEmail(requestUser.getEmail());
//		userRepository.save(user);

		// 3. @Transactional 추가하고 save()를 제거 함
		// 이렇게 하면 Dirty Checking 이 되면서 알아서 update 해줌
		// user 객체의 내용을 바꾸면 JPA -> Persistence Context -> 1차 캐시의 내용이 바뀌고, commit 할 때 flush 되면서 DB에 들어감
		User user = userRepository.findById(id).orElseThrow( () -> new NoSuchElementException("수정할 대상 없음"));
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());  

		
		return user;
		
	}
	
	
	
	
	@GetMapping("/dummy/user")
	public List<User> list() {
		return userRepository.findAll();
	}

	// 한페이지당 2건의 데이터를 리턴받아 볼 예정
	// /dummy/user/page?page={페이지 번호(0부터 시작)}&size={한페이지당 요청 개수} 등의 파라미터 추가 가능
	@GetMapping("/dummy/user/page")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable) {
		Page<User> pageUser = userRepository.findAll(pageable);
		
		if (pageUser.isFirst() ) {
			// 이런 식으로 페이징 가능 로직 추가 가능
		}
		
		List<User> users = pageUser.getContent();
		return users;
	}
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// 데이터가 없을 때를 대비하여 Optional<>로 반환한다.  
		// 없을 때 Exception 처리를 이렇게 한다고 그러네... 
		User user = userRepository.findById(id).orElseThrow(new Supplier<NoSuchElementException>() {
			@Override
			public NoSuchElementException get() {
				return new NoSuchElementException("해당 유저는 없습니다. id: " + id);
			}
		}); 
//   람다식으로 쓰면 짧게 쓸 수 있음		
//		User user = userRepository.findById(id).orElseThrow(
//				() ->  new NoSuchElementException("해당 유저는 없습니다. id: " + id)
//		);
		
		
		// MessageConverter가 응답시에 작동하여 Java Object를 Jackson 라이브러리를 사용하여 json 으로 바꿔줌 
		return user;
		
	}
	
	
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
