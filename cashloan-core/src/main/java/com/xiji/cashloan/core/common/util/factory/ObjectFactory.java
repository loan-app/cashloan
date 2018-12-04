package com.xiji.cashloan.core.common.util.factory;

import com.xiji.cashloan.core.common.exception.ObjectNotFoundException;

/**
 * 对象工厂
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 * @param <T>
 */
public interface ObjectFactory<T> {
	/**
	 * 从工厂中获取对象
	 * @param qualifier 限定符
	 * @return
	 * @throws ObjectNotFoundException
	 */
	T getObject(Object qualifier) throws ObjectNotFoundException;
}
