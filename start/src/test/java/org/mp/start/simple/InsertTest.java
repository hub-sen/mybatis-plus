package org.mp.start.simple;

import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/20 16:58
 * </pre>
 */
public class InsertTest extends StartApplicationTests {

	@Test
	public void insert() {
		User user = new User();
		user.setRealName("向东");
		user.setAge(21);
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		user.setRemark("这是备注信息");
		int insert = userMapper.insert(user);
		System.out.println("insert = " + insert);
	}
}
