package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.blog.model.User;

// DAO 역할 수행
// 자동으로 bean 등록 됨 => @Repository  생략 가능
public interface UserRepository extends JpaRepository<User, Integer> {

}
