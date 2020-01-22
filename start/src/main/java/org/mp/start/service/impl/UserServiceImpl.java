package org.mp.start.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.mp.start.dao.UserMapper;
import org.mp.start.entity.User;
import org.mp.start.service.UserService;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/21 21:01
 * </pre>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
