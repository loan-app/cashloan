package com.xiji.cashloan.cl.mapper;

import com.github.pagehelper.Page;
import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.model.ChannelCountModel;
import com.xiji.cashloan.cl.model.ChannelModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.mapper.RDBatisDao;

import java.util.List;
import java.util.Map;

/**
 * 渠道信息Dao
 *
 * @author wnb
 * @version 1.0.0
 * @date 2018/12/03
 *
 *
 * 
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@RDBatisDao
public interface ChannelMapper extends BaseMapper<Channel,Long> {
	
	/**
	 * 根据条件查询主键
	 */
	long findIdSelective(Map<String, Object> paramMap);
	
	/**
	 * 根据条件查询对象
	 */
	Channel findSelective(Map<String, Object> paramMap);

	/**
	 * 列表查询
	 * @param paramMap
	 * @return
	 */
	List<ChannelModel> page(Map<String, Object> paramMap);
	/**
	 * 渠道用户信息统计
	 * @param searchMap
	 * @return
	 */
	Page<ChannelCountModel> channelUserList(Map<String, Object> searchMap);

	/**
	 * 根据渠道编码查询渠道信息
	 */
	Channel getChannelByCode(String code);

	/**
	 * 根据id查询渠道信息
	 */
	Channel getChannelById(Long id);

	/**
	 * 查出所有渠道信息
	 */
	List<Channel> listChannel();
	
	/**
	 * 查询没有版本信息的渠道id和名称
	 */
	List<Channel> listChannelWithoutApp();
	
	/**
	 * 渠道标识，渠道名称
	 */
	List<Map<String, Object>> countOne(Map<String,Object> paramMap);
	
	/**
	 * 注册人数
	 */
	List<Map<String, Object>> countTwo(Map<String,Object> paramMap);
	
	/**
	 * 借款人数
	 */
	List<Map<String, Object>> countThree(Map<String,Object> paramMap);
	
	/**
	 * 借款次数
	 */
	List<Map<String, Object>> countFour(Map<String,Object> paramMap);
	
	/**
	 * 借款金额
	 */
	List<Map<String, Object>> countFive(Map<String,Object> paramMap);
	
	/**
	 * 放款新增笔数
	 */
	List<Map<String, Object>> countSix(Map<String,Object> paramMap);
	
	/**
	 * 放款复借笔数
	 */
	List<Map<String, Object>> countSeven(Map<String,Object> paramMap);
	
	/**
	 * 放款成功金额
	 */
	List<Map<String, Object>> countEight(Map<String,Object> paramMap);

	/**
	 * 单一渠道名称count
     */
	List<Map<String, Object>> countOneByName(Map<String,Object> paramMap);

	/**
	 * 首贷逾期数
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> countFirstMortgageOverdue(Map<String,Object> paramMap);

	/**
	 * 逾期总数
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> countMortgageOverdue(Map<String,Object> paramMap);

	/**
	 * 单一渠道放款总笔数
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> countLending(Map<String,Object> paramMap);


	/**
	 * 查询渠道配置
	 * @return
	 */
	List<Map<String, Object>> queryChannelConfig();

	/**
	 * 认证人数
	 * @param paramMap
	 * @return
	 */
	List<Map<String, Object>> certificationCount(Map<String,Object> paramMap);
}
