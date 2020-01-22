package org.mp.high.simple;

import org.junit.jupiter.api.Test;
import org.mp.high.HighApplicationTests;
import org.mp.high.entity.User;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description  测试自动填充
 * @author shishi
 * 2020/1/22 19:19
 * </pre>
 */
public class AutoFillTest extends HighApplicationTests {

	@Test
	public void autoFill() {
		User user = new User();
		user.setId(1219943246422835202L);
		user.setAge(22);
		// effectNums = userMapper.insert(user);
		effectNums = userMapper.updateById(user);
	}

	@Test
	public void autoFill2() {
		User user = new User();
		user.setName("璨然");
		user.setAge(23);
		user.setEmail("cr@2020.com");
		user.setCreateTime(LocalDateTime.now());
		user.setManagerId(1219943246422835202L);
		effectNums = userMapper.insert(user);
	}
}
