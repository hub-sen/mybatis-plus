package org.mp.high.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/22 15:27
 * </pre>
 */
@Data
public class User {
	/**
	 * 主键
	 */
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private Integer age;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 直属上级
	 */
	private Long managerId;
	/**
	 * 创建时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 版本
	 */
	private Integer version;
	/**
	 * 逻辑删除标记(0: 未删除, 1: 已删除)
	 */
	@TableLogic
	@TableField(select = false)
	private Integer deleted;
}
