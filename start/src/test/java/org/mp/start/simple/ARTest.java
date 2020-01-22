package org.mp.start.simple;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 20:04
 * </pre>
 */
public class ARTest extends StartApplicationTests {

	@Test
	public void ARTestInsert() {
		User user = new User();
		user.setRealName("璨然");
		user.setAge(25);
		user.setEmail("cr@baomidou.com");
		user.setManagerId(1088248166370832385L);
		boolean insert = user.insert();
		System.out.println("insert = " + insert);
	}

	@Test
	public void ARTestSelectById() {
		User user = new User();
		User selectById = user.selectById(1219592715569344514L);
		System.out.println("(user == selectById) = " + (user == selectById));
		System.out.println("selectById = " + selectById);
	}

	@Test
	public void ARTestSelectById2() {
		User user = new User();
		user.setUserId(1219592715569344514L);
		User selectById = user.selectById();
		System.out.println("(user == selectById) = " + (user == selectById));
		System.out.println("selectById = " + selectById);
	}

	@Test
	public void ARTestUpdateById() {
		User user = new User();
		user.setUserId(1219592715569344514L);
		user.setEmail("cr2020@baomidou.com");
		boolean updated = user.updateById();
		System.out.println("updated = " + updated);
	}

	@Test
	public void ARTestDeleteById() {
		User user = new User();
		user.setUserId(1219592715569344514L);
		boolean deleted = user.deleteById();
		System.out.println("deleted = " + deleted);
	}

	@Test
	public void testInsertOrUpdate() {
		User user = new User();
		user.setUserId(1219596430477062145L);
		user.setRealName("施森");
		user.setEmail("shisen@baomidou.com");
		boolean b = user.insertOrUpdate();
		System.out.println("b = " + b);
	}

}
