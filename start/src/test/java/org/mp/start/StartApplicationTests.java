package org.mp.start;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mp.start.dao.UserMapper;
import org.mp.start.entity.User;
import org.mp.start.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class StartApplicationTests {

	@Autowired
	public UserMapper userMapper;

	@Autowired
	public UserService userService;

	@Test
	void contextLoads() {
	}

	@Test
	public void select() {
		List<User> users = userMapper.selectList(null);

		Assert.assertEquals(5, users.size());

		users.forEach(System.out::println);
	}
}
