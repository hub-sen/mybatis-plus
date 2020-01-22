package org.mp.high.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
import org.mp.high.entity.User;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/22 15:31
 * </pre>
 */
public interface UserMapper extends BaseMapper<User> {

	List<User> mySelect(@Param(Constants.WRAPPER) @NonNull Wrapper<User> wrapper);
}
