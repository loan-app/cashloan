package com.xiji.cashloan.core.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具类-分表
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
public class ShardTableUtil {
	
	public static List<String> tables = new ArrayList<String>();
	static{
		tables.add("cl_user_contacts");
		tables.add("cl_operator_voice");
		tables.add("cl_operator_sms");
		tables.add("cl_operator_net");
		tables.add("cl_operator_voice_cnt");
		tables.add("cl_user_messages");
	}
	
	/**
	 * 根据主键Id生成分表名称
	 * @param shardId 拆分id段
	 * @return
	 */
	public static String generateTableNameById(String tableName, long id, long shardId){
		if(tables.contains(tableName)){
			long num = id/shardId + 1;
			return tableName + "_" + num;
		}else{
			return tableName;
		}
	}

}
