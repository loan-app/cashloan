package com.xiji.cashloan.cl.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.xiji.cashloan.cl.domain.Channel;
import com.xiji.cashloan.cl.mapper.ClBorrowMapper;
import com.xiji.cashloan.cl.mapper.UserAuthMapper;
import com.xiji.cashloan.cl.model.ChannelModel;
import com.xiji.cashloan.cl.service.ChannelService;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.mapper.ChannelMapper;
import com.xiji.cashloan.cl.model.ChannelCountModel;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.mapper.UserMapper;

/**
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 */
@Service("channelService")
public class ChannelServiceImpl extends BaseServiceImpl<Channel, Long> implements ChannelService {
	
    @Resource
    private ChannelMapper channelMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserAuthMapper userAuthMapper;
    @Resource
	private ClBorrowMapper clBorrowMapper;
    
    
    @Override
	public BaseMapper<Channel, Long> getMapper() {
		return channelMapper;
	}

	@Override
	public boolean save(Channel channel) {
		channel.setCreateTime(new Date());
		channel.setState(ChannelModel.STATE_ENABLE);
		int result = channelMapper.save(channel);
		if(result >0 ){
			return true;
		}
		return false;
	}
	
	@Override
	public boolean update(Map<String, Object> paramMap) {
		int result = channelMapper.updateSelective(paramMap);
		if (result > 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public long findIdByCode(String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		return channelMapper.findIdSelective(paramMap);
	}
	
	@Override
	public Channel findByCode(String code) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("code", code);
		return channelMapper.findSelective(paramMap);
	}

	@Override
	public Page<ChannelModel> page(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ChannelModel> page = (Page<ChannelModel>) channelMapper
				.page(searchMap);
		return page;
	}

	@Override
	public Page<ChannelCountModel> channelUserList(int current, int pageSize,
			Map<String, Object> searchMap) {
		PageHelper.startPage(current, pageSize);
		Page<ChannelCountModel> page = (Page<ChannelCountModel>) channelMapper.channelUserList(searchMap);
		return page;
	}

	@Override
	public List<Channel> listChannel() {
		return channelMapper.listChannel();
	}

	@Override
	public List<Channel> listChannelWithoutApp() {
		return channelMapper.listChannelWithoutApp();
	}

	@Override
	public List<Map<String, Object>> channelUserCount(Map<String, Object> paramMap,int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) channelMapper.countOne(paramMap);
		List<Map<String, Object>> one = result.getResult();
		countResult(one, paramMap);
		return result;
	}
	
	private void count(Map<String,Object> oneMap,List<Map<String,Object>> listMap,String remark,String keyWord){
		String s;
		for (Map<String, Object> map : listMap) {
			s=map.get("channelId")+"";
			if(s!=null&&!s.equals("")&&!s.equals("null")&&s.equals(oneMap.get("channelId")+"")){
				oneMap.put(remark, map.get(keyWord)==null?"0":map.get(keyWord));
				break;
			}
		}
		if(oneMap.get(remark)==null||oneMap.get(remark).equals("")){
			oneMap.put(remark,"0");
		}
	}
	
	@Override
	public List<Map<String,Object>> conversion(Map<String, Object> params) throws Exception{
		List<Map<String,Object>> list = new ArrayList<>();
		Date startTime = new Date();
		Date endTime = new Date();
		if (params.isEmpty()) {
			startTime = DateUtil.getDateBefore(-30, endTime);
		}else {
			endTime = DateUtil.valueOf(params.get("afterTime").toString());
			startTime = DateUtil.valueOf(params.get("beforeTime").toString());
		}
		List<Channel> channelList = channelMapper.listSelective(params);
		for (Channel channel : channelList) {
			Map<String,Object> data = new HashMap<>();
			data.put("code", channel.getCode());
			data.put("name", channel.getName());
			
			Map<String,Object> search = new HashMap<>();
			search.put("endTime", endTime);
			search.put("startTime", startTime);
			search.put("channelId", channel.getId());
			
			String registerCount = userMapper.registerCount(search);
			data.put("registerCount", registerCount==null?"0":registerCount);
			
			String channelId = channel.getId().toString();
			
			String idCount = userAuthMapper.idCount(channelId);
			data.put("idCount", idCount==null?"0":idCount);
			
			String contactCount = userAuthMapper.contactCount(channelId);
			data.put("contactCount", contactCount==null?"0":contactCount);
			
			String phoneCount = userAuthMapper.phoneCount(channelId);
			data.put("phoneCount", phoneCount==null?"0":phoneCount);
			
			String bankCount = userAuthMapper.bankCount(channelId);
			data.put("bankCount", bankCount==null?"0":bankCount);
			
			String zhimaCount = userAuthMapper.zhimaCount(channelId);
			data.put("zhimaCount", zhimaCount==null?"0":zhimaCount);
			
			String workCount = userAuthMapper.workCount(channelId);
			data.put("workCount", workCount==null?"0":workCount);
			
			String borrowCount = clBorrowMapper.borrowCountByUserId(channelId);
			data.put("borrowCount", borrowCount==null?"0":borrowCount);
			
			String borrowSum = clBorrowMapper.borrowSumByUserId(channelId);
			data.put("borrowSum", borrowSum==null?"0":borrowSum);
			
			String borrowSuccessCount = clBorrowMapper.borrowSuccessCountByUserId(channelId);
			data.put("borrowSuccessCount", borrowSuccessCount==null?"0":borrowSuccessCount);
			
			String borrowSuccessSum = clBorrowMapper.borrowSuccessSumByUserId(channelId);
			data.put("borrowSuccessSum", borrowSuccessSum==null?"0":borrowSuccessSum);
			
			String loanCount = clBorrowMapper.loanCountByUserId(channelId);
			data.put("loanCount", loanCount==null?"0":loanCount);
			
			String loanSum = clBorrowMapper.loanSumByUserId(channelId);
			data.put("loanSum", loanSum==null?"0":loanSum);
			
			String payCount = clBorrowMapper.payCountByUserId(channelId);
			data.put("payCount", payCount==null?"0":payCount);
			
			String realPayCount = clBorrowMapper.realPayCountUserId(channelId);
			data.put("realPayCount", realPayCount==null?"0":realPayCount);
			
			String overdueCount = clBorrowMapper.overdueCountByUserId(channelId);
			data.put("overdueCount", overdueCount==null?"0":overdueCount);
			
			double rateOne = 0;
			if (Double.valueOf(registerCount)>0) {
				rateOne = Double.valueOf(idCount)/Double.valueOf(registerCount);
			}
			data.put("rateOne", rateOne*100);
			
			double rateTwo = 0;
			if (Double.valueOf(registerCount)>0) {
				rateTwo = Double.valueOf(borrowCount)/Double.valueOf(registerCount);
			}
			data.put("rateTwo", rateTwo*100);
			
			double rateThree = 0;
			if (Double.valueOf(registerCount)>0) {
				rateOne = Double.valueOf(loanCount)/Double.valueOf(registerCount);
			}
			data.put("rateThree", rateThree*100);
			
			double rateFour = 0;
			if (Double.valueOf(overdueCount)>0) {
				rateFour = Double.valueOf(loanCount)/Double.valueOf(overdueCount);
			}
			data.put("rateFour", rateFour*100);
			
			list.add(data);
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> oneChannelUserCount(Map<String, Object> paramMap, int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		Page<Map<String, Object>> result = (Page<Map<String, Object>>) channelMapper.countOneByName(paramMap);
		List<Map<String, Object>> one = result.getResult();
		countResult(one, paramMap);
		return result;
	}

	public String listToString(List<String> list, String separator) {    
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);    
		}
		return sb.toString().substring(0,sb.toString().length()-1);
	}

	private void countResult(List<Map<String, Object>> one, Map<String, Object> paramMap) {
		//关联的是渠道id，传进来的搜索条件是渠道名称，这边转换下
		for (Map<String, Object> map : one) {
			if(paramMap.get("name")!=null){
				paramMap.put("channelId", map.get("channelId"));
			}
			List<Map<String, Object>> two=channelMapper.countTwo(paramMap);
			List<Map<String, Object>> three=channelMapper.countThree(paramMap);
			List<Map<String, Object>> four=channelMapper.countFour(paramMap);
			List<Map<String, Object>> five=channelMapper.countFive(paramMap);
			List<Map<String, Object>> six=channelMapper.countSix(paramMap);
			List<Map<String, Object>> seven=channelMapper.countSeven(paramMap);
			List<Map<String, Object>> eight=channelMapper.countEight(paramMap);
			//渠道标识，渠道名称
			//注册人数
			count(map, two, "registerCount", "countTwo");
			//借款人数
			count(map, three, "borrowMember", "countThree");
			//借款次数
			count(map, four, "borrowCount", "countFour");
			//借款金额
			count(map, five, "borrowAmout", "countFive");
			//首贷放款笔数
			count(map, six, "newPayCount", "countSix");
			//再贷放款笔数
			count(map, seven, "repeatPayCount", "countSeven");
			//放款成功金额
			count(map, eight, "payAccount", "countEight");
		}
	}
}