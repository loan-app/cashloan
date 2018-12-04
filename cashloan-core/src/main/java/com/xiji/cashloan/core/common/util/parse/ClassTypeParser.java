package com.xiji.cashloan.core.common.util.parse;

/**
 * Class类型解析器
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 */
public interface ClassTypeParser {
	<T> T parse(String content, Class<T> valueType);
}
