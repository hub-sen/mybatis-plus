package org.mp.high.simple;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.mp.high.HighApplicationTests;
import org.mp.high.entity.User;

import java.util.List;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/22 15:43
 * </pre>
 */
public class DeleteTest extends HighApplicationTests {

	@Test
	public void deleteById() {
		int deleted = userMapper.deleteById(1094592041087729666L);
		System.out.println("影响行数 = " + deleted);
	}


	@Test
	public void select() {
		users = userMapper.selectList(null);
	}

	@Test
	public void updateById() {
		User user = new User();
		user.setAge(26);
		user.setId(1088248166370832385L);
		effectNums = userMapper.updateById(user);
	}

	@Test
	public void mySelect() {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery().ge(User::getAge, 20);
		users = userMapper.mySelect(queryWrapper);
	}
}
