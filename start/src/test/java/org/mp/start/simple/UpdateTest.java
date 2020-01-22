package org.mp.start.simple;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 19:17
 * </pre>
 */
public class UpdateTest extends StartApplicationTests {


	@Test
	public void testUpdateById() {
		User user = new User();
		user.setUserId(1087982257332887553L);
		user.setAge(41);

		int i = userMapper.updateById(user);
	}

	@Test
	public void testUpdateByWrapper() {
		User user = new User();
		user.setAge(42);
		user.setEmail("boss2@baomidou.com");

		LambdaUpdateWrapper<User> updateWrapper = Wrappers.lambdaUpdate();
		updateWrapper.eq(User::getUserId, 1087982257332887553L);

		int update = userMapper.update(user, updateWrapper);
		System.out.println("update = " + update);
	}

	@Test
	public void testUpdateByWrapper2() {
		LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.lambdaUpdate();
		lambdaUpdateWrapper.set(User::getAge, 43).set(User::getEmail, "boss2020@baomidou.com").eq(User::getRealName, "大boss");
		int update = userMapper.update(null, lambdaUpdateWrapper);
		System.out.println("update = " + update);
	}

	@Test
	public void testUpdateByLambdaWrapper() {

		LambdaUpdateChainWrapper<User> lambdaUpdateChainWrapper = ChainWrappers.lambdaUpdateChain(userMapper);
		boolean updated = lambdaUpdateChainWrapper.set(User::getAge, 44).eq(User::getRealName, "大boss").update();

		System.out.println("updated = " + updated);
	}
}
