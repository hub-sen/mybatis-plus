package org.mp.high.component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * <pre>
 * Description
 * @author shishi
 * 2020/1/22 19:16
 * </pre>
 */
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
	@Override
	public void insertFill(MetaObject metaObject) {

		boolean createTime = metaObject.hasGetter("createTime");
		Object val = getFieldValByName("createTime", metaObject);

		if (createTime && ObjectUtils.isNull(val)) {
			System.out.println("insert ~~~");
			setFieldValByName("createTime", LocalDateTime.now(), metaObject);
		}

	}

	@Override
	public void updateFill(MetaObject metaObject) {

		boolean updateTime = metaObject.hasGetter("updateTime");
		Object val = getFieldValByName("updateTime", metaObject);

		if (updateTime && ObjectUtils.isNull(val)) {
			System.out.println("update ~~~");
			setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
		}


	}
}
