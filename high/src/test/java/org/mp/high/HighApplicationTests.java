package org.mp.high;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mp.high.dao.UserMapper;
import org.mp.high.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class HighApplicationTests {

	@Autowired
	public UserMapper userMapper;

	/**
	 * 结果集
	 */
	public List<User> users;

	/**
	 * 结果
	 */
	public User user;

	/**
	 * 影响行数
	 */
	public Integer effectNums;

	/**
	 * 是否成功
	 */
	public Boolean flag;


	@AfterEach
	public void after() {
		Optional.ofNullable(users).ifPresent(users -> users.forEach(System.out::println));
		Optional.ofNullable(user).ifPresent(System.out::println);
		Optional.ofNullable(effectNums).ifPresent(t -> System.out.println("受影响行数: " + t));
		Optional.ofNullable(flag).ifPresent(t -> System.out.println("是否成功: " + t));

	}

}
