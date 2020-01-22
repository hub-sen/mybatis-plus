package org.mp.start.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.SqlCondition;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/20 16:15
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("mp_user")
public class User extends Model<User> {
	/**
	 * 主键
	 */
	@TableId(value = "user_id",type = IdType.NONE)
	private Long userId;
	/**
	 * 姓名
	 */
	@TableField(value = "name", condition = SqlCondition.LIKE)
	private String realName;
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
	 * 备注信息
	 */
	@TableField(exist = false)
	private String remark;
}
