package org.mp.start.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 21:04
 * </pre>
 */
class UserServiceImplTest extends StartApplicationTests {

	List<User> users;

	User user;

	Boolean flag;

	@AfterEach
	public void after() {
		Optional.ofNullable(users).ifPresent(lists -> lists.forEach(System.out::println));
		Optional.ofNullable(user).ifPresent(user -> System.out.println("user = " + user));
		Optional.ofNullable(flag).ifPresent(flag -> System.out.println("flag = " + flag));
	}

	@Test
	public void getOne() {
		user = userService.getOne(Wrappers.<User>lambdaQuery().ge(User::getAge, 10), false);
	}

	@Test
	public void saveOrUpdateBatch() {
		User user1 = new User();
		user1.setRealName("施森1");
		user1.setEmail("us1@baomidou.com");
		User user2 = new User();
		user2.setUserId(1219608780609363969L);
		user2.setRealName("施森2");
		user2.setEmail("us2@baomidou.com");
		User user3 = new User();
		user3.setUserId(1219608780609363970L);
		user3.setRealName("施森3");
		user3.setEmail("us3@baomidou.com");

		flag = userService.saveOrUpdateBatch(Arrays.asList(user1, user2, user3));
	}

	@Test
	public void chain() {
		users = userService.lambdaQuery()
				.ge(User::getAge, 10)
				.like(User::getRealName, "雨")
				.list();
	}
}