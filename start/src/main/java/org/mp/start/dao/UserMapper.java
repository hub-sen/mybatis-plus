package org.mp.start.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.mp.start.entity.User;

import java.util.List;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/20 16:20
 * </pre>
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);

	Page<User> selectMyPage(Page<User> page, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
