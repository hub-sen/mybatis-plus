package org.mp.start.simple;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 19:43
 * </pre>
 */
public class DeleteTest extends StartApplicationTests {

	@Test
	public void deleteById() {
		int delete = userMapper.deleteById(1219191597978849281L);
		System.out.println("delete = " + delete);
	}

	@Test
	public void deleteByMap() {
		HashMap<String, Object> whereMap = new HashMap<>();
		whereMap.put("name", "向西");
		int delete = userMapper.deleteByMap(whereMap);
		System.out.println("delete = " + delete);
	}

	@Test
	public void deleteByIds() {
		List<Long> ids = Arrays.asList(1219189762157162498L, 1219188826454073346L);

		int deleteBatchIds = userMapper.deleteBatchIds(ids);

		System.out.println("deleteBatchIds = " + deleteBatchIds);
	}

	@Test
	public void deleteByWrapper() {
		LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
		lambdaQueryWrapper.eq(User::getAge, 21).eq(User::getRealName, "向南");
		int delete = userMapper.delete(lambdaQueryWrapper);
		System.out.println("delete = " + delete);
	}
}
