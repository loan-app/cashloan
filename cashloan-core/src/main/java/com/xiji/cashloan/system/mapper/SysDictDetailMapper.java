package com.xiji.cashloan.system.mapper;

import java.util.List;
import java.util.Map;

import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;
import com.xiji.cashloan.system.domain.SysDictDetail;
import org.apache.ibatis.annotations.Param;

/**
 * DAO接口
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@RDBatisDao
public interface SysDictDetailMapper extends BaseMapper<SysDictDetail,Long> {

	
	
	int deleteByPrimary(Long id);
	
	
	Long getCount(Map<String, Object> data);

	/**
	 * @description
	 * @return
	 * @author liyc
	 * @return List<Map<String,Object>>
	 * @since  1.0.0
	*/
	List<Map<String, Object>> queryAllDic();

	List<Map<String, Object>> getPageListMap(Map<String, Object> data);

	/**
	 * 根据父类ID获取详细值
	 * @param parentId
	 * @return
	 */
	List<String> getItemVlueByParentId(String parentId);
	
	/**
	 * 查询数据字典详情
	 * @param code
	 * @param parentName
	 * @return
	 */
	SysDictDetail findDetail(@Param("code")String code, @Param("parentName")String parentName);

	/**
	 * 根据父级类型名称查询字典列表
	 * @param parentName
	 * @return
	 */
	List<Map<String, Object>> queryAllDicByParentName(@Param("parentName")String parentName);

	/**
	 * 新增时查询额度类型名称
	 * @param data
	 * @return
	 */
	List<SysDictDetail> listByTypeCode(Map<String, Object> data);

	/**
	 * 修改时查询额度类型名称
	 * @param data
	 * @return
	 */
	List<SysDictDetail> listUpdateCode(Map<String, Object> data);
}
