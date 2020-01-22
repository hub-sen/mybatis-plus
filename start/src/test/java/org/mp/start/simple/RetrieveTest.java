package org.mp.start.simple;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.ChainWrappers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mp.start.StartApplicationTests;
import org.mp.start.entity.User;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/20 17:39
 * </pre>
 */
public class RetrieveTest extends StartApplicationTests {


	private List<User> users;

	@AfterEach
	public void after() {
		Optional.ofNullable(users).ifPresent(users -> users.forEach(System.out::println));
	}

	@Test
	public void selectById() {
		User user = userMapper.selectById(1087982257332887553L);
		System.out.println("user = " + user);
	}

	@Test
	public void selectByIds() {
		List<Long> idsList = Arrays.asList(1087982257332887553L, 1219187907683414018L, 1219188826454073346L);
		users = userMapper.selectBatchIds(idsList);
	}

	@Test
	public void selectByMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("user_id", 1087982257332887553L);
		map.put("name", "向东");

		users = userMapper.selectByMap(map);
	}

	/*
	名字中包含雨并且年龄小于40
	name like '%雨%' and age<40
	 */
	@Test
	public void selectByWrapper() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "雨");
		query.le("age", 40);

		users = userMapper.selectList(query);

	}

	/*
	名字中包含雨年并且龄大于等于20且小于等于40并且email不为空

   name like '%雨%' and age between 20 and 40 and email is not null
	 */
	@Test
	public void selectByWrapper2() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "雨").between("age", 20, 40).isNotNull("email");

		users = userMapper.selectList(query);
	}

	/*
	名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列

   name like '王%' or age>=25 order by age desc,id asc
	 */
	@Test
	public void selectByWrapper3() {
		QueryWrapper<User> query = Wrappers.query();
		query.likeRight("name", "雨").or().ge("age", 25).orderByDesc("age").orderByAsc("user_id");

		users = userMapper.selectList(query);
	}

	/*
	创建日期为2019年2月14日并且直属上级为名字为王姓

      date_format(create_time,'%Y-%m-%d')='2019-02-14' and manager_id in (select user_id from mp_user where name like '王%')
	 */
	@Test
	public void selectByWrapper4() {
		QueryWrapper<User> query = Wrappers.query();
		query.apply("date_format(create_time,'%Y-%m-%d') = {0}", "2019-02-14")
				.inSql("manager_id", "select user_id from mp_user where name like '王%'");

		users = userMapper.selectList(query);
	}

	/*
	名字为王姓并且（年龄小于40或邮箱不为空）

    name like '王%' and (age<40 or email is not null)
	 */
	@Test
	public void selectByWrapper5() {
		QueryWrapper<User> query = Wrappers.query();
		query.likeRight("name", "王").and(wq -> wq.le("age", 40).isNotNull("age"));

		users = userMapper.selectList(query);
	}

	/*
	名字为王姓或者（年龄小于40并且年龄大于20并且邮箱不为空）

    name like '王%' or (age<40 and age>20 and email is not null)
	 */
	@Test
	public void selectByWrapper6() {
		QueryWrapper<User> query = Wrappers.query();
		query.likeRight("name", "王").or(wq -> wq.between("age", 20, 40).isNotNull("email"));

		users = userMapper.selectList(query);
	}

	/*
	（年龄小于40或邮箱不为空）并且名字为王姓

    (age<40 or email is not null) and name like '王%'
	 */
	@Test
	public void selectByWrapper7() {
		QueryWrapper<User> query = Wrappers.query();
		// query.likeRight("name", "王").and(wq -> wq.le("age", 40).or().isNotNull("email"));
		query.nested(wq -> wq.le("age", 40).isNotNull("email")).likeRight("name", "王");
		users = userMapper.selectList(query);
	}

	/*
	年龄为30、31、34、35

    age in (30、31、34、35)
	 */
	@Test
	public void selectByWrapper8() {
		QueryWrapper<User> query = Wrappers.query();
		query.in("age", 30, 31, 34, 35);
		users = userMapper.selectList(query);
	}

	/*
	只返回满足条件的其中一条语句即可
	limit 1
	 */
	@Test
	public void selectByWrapper9() {
		QueryWrapper<User> query = Wrappers.query();
		query.in("age", 30, 31, 34, 35).last("limit 1");
		users = userMapper.selectList(query);
	}

	/*
	名字中包含雨并且年龄小于40(需求1加强版)
	select id,name

	           from user

	           where name like '%雨%' and age<40

   select id,name,age,email

			   from user

			   where name like '%雨%' and age<40
	 */
	@Test
	public void selectByWrapper10() {
		QueryWrapper<User> query = Wrappers.query();
		// query.select("user_id", "name").like("name", "雨").le("age", 40);
		query.select(User.class, info -> !info.getColumn().equals("created_time") && !info.getColumn().equals("manager_id"))
				.like("name", "雨")
				.le("age", 40);
		users = userMapper.selectList(query);
	}


	@Test
	public void testCondition() {
		condition("王", "");
	}

	private void condition(String name, String email) {
		QueryWrapper<User> query = Wrappers.query();
		/*if (StringUtils.isNotBlank(name)) {
			query.like("name", name);
		}
		if (StringUtils.isNotBlank(email)) {
			query.like("email", email);
		}
*/
		query.like(StringUtils.isNotBlank(name), "name", name).like(StringUtils.isNotBlank(email), "email", email);
		users = userMapper.selectList(query);
	}

	@Test
	public void testWrapperEntity() {
		User whereUser = new User();
		whereUser.setRealName("向东");
		whereUser.setAge(21);
		QueryWrapper<User> query = Wrappers.query(whereUser);

		users = userMapper.selectList(query);
	}

	@Test
	public void testWrapperAllEq() {
		QueryWrapper<User> query = Wrappers.query();
		HashMap<String, Object> params = new HashMap<>();
		params.put("name", "王");
		params.put("age", 25);
		params.put("email", null);
		// query.allEq(params,false);
		query.allEq((k, v) -> k.equals("name"), params);
		users = userMapper.selectList(query);
	}

	@Test
	public void selectByWrapperMaps() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "雨");
		query.le("age", 40);
		query.select("name", "email");

		List<Map<String, Object>> maps = userMapper.selectMaps(query);

		System.out.println("maps = " + maps);

	}

	/*
	按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。

	并且只取年龄总和小于500的组。

	select avg(age) avg_age,min(age) min_age,max(age) max_age

	from user
	group by manager_id

	having sum(age) <500
	 */
	@Test
	public void selectByWrapperMaps2() {
		QueryWrapper<User> query = Wrappers.query();
		query.select("manager_id", "avg(age) avg_age", "min(age) min_age", "max(age) max_age")
				.groupBy("manager_id")
				.having("sum(age) < {0}", 500);

		List<Map<String, Object>> maps = userMapper.selectMaps(query);

		System.out.println("maps = " + maps);

	}

	@Test
	public void selectByWrapperObjs() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "雨");
		query.le("age", 40);
		query.select("name", "email");

		List<Object> maps = userMapper.selectObjs(query);

		System.out.println("maps = " + maps);

	}

	@Test
	public void selectByWrapperCount() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "雨");
		query.le("age", 40);

		Integer count = userMapper.selectCount(query);

		System.out.println("count = " + count);

	}

	@Test
	public void selectByWrapperOne() {
		QueryWrapper<User> query = Wrappers.query();
		query.like("name", "红雨");
		query.le("age", 40);

		User user = userMapper.selectOne(query);

		System.out.println("user = " + user);

	}

	@Test
	public void selectByWrapperLambda() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
		lambdaQuery.like(User::getRealName, "雨").lt(User::getAge, 40);
		users = userMapper.selectList(lambdaQuery);
	}

	/*
	名字为王姓并且（年龄小于40或邮箱不为空）

	name like '王%' and (age<40 or email is not null)
	 */
	@Test
	public void selectByWrapperLambda2() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
		lambdaQuery.likeRight(User::getRealName, "王")
				.and(lwq -> lwq.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		List<User> users = userMapper.selectList(lambdaQuery);
		users.forEach(System.out::println);
	}

	@Test
	public void selectByWrapperLambda3() {
		List<User> users = ChainWrappers.lambdaQueryChain(userMapper).like(User::getRealName, "王")
				.and(lwq -> lwq.lt(User::getAge, 40).or().isNotNull(User::getEmail)).list();

		users.forEach(System.out::println);
	}

	@Test
	public void selectMy() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
		lambdaQuery.likeRight(User::getRealName, "王")
				.and(lwq -> lwq.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		List<User> users = userMapper.selectAll(lambdaQuery);
		users.forEach(System.out::println);
	}


	@Test
	public void selectPage() {
		Page<User> page = new Page<>(1, 2, false);
		Page<User> userPage = ChainWrappers.lambdaQueryChain(userMapper).ge(User::getAge, 26).page(page);

		System.out.println("userPage.getPages() = " + userPage.getPages());
		System.out.println("userPage.getTotal() = " + userPage.getTotal());
		userPage.getRecords().forEach(System.out::println);

	}

	@Test
	public void selectMyPage() {
		Page<User> page = new Page<>(1, 2);
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();
		lambdaQuery.ge(User::getAge, 26);

		Page<User> userPage = userMapper.selectMyPage(page, lambdaQuery);

		System.out.println("userPage.getPages() = " + userPage.getPages());
		System.out.println("userPage.getTotal() = " + userPage.getTotal());
		userPage.getRecords().forEach(System.out::println);

	}

}
