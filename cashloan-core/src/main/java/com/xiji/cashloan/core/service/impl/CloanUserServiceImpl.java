package com.xiji.cashloan.core.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.model.ChannelWarnModel;
import com.xiji.cashloan.core.service.CloanUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import tool.util.DateUtil;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.mapper.UserMapper;
import com.xiji.cashloan.core.model.CloanUserModel;
import com.xiji.cashloan.system.mapper.SysDictDetailMapper;

/**
 * 用户认证ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 *  
 * 未经授权不得进行修改、复制、出售及商业使用
 */

@Service("cloanUserService")
public class CloanUserServiceImpl extends BaseServiceImpl<User, Long> implements CloanUserService {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CloanUserServiceImpl.class);

	@Resource
	private UserMapper userMapper;
	@Resource
	private SysDictDetailMapper sysDictDetailMapper;
	
	@Override
	public BaseMapper<User, Long> getMapper() {
		return userMapper;
	}

	@Override
	public Page<CloanUserModel>   listUser(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<CloanUserModel> list = userMapper.listModel(params);
		return (Page<CloanUserModel>) list;
	}

	@Override
	public Page<CloanUserModel> listUserNotBorrowagain(Map<String, Object> params,
										 int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<CloanUserModel> list = userMapper.listNotBorrowAgain(params);
		return (Page<CloanUserModel>) list;
	}

	@Override
	public CloanUserModel getModelById(Long id) {
		CloanUserModel model = userMapper.getModel(id);
		return model;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> findAllDic() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>(); 
		List<Map<String, Object>> list = userMapper.queryAllDic();
		if (list != null && !list.isEmpty()) {
			for (Map<String, Object> o : list) {
				Map<String, Object> fmap = new HashMap<String, Object>();
				String typeCode = o.get("typeCode").toString();
				List<Map<String, Object>> zlist = new ArrayList<Map<String, Object>>();
				if (!result.isEmpty()) {
			    	boolean flag=false;
			    	 for (Map<String, Object> r: result) {
			    		 if(r.containsKey(typeCode)){
			    			 flag=true; 
			    			 zlist=(List<Map<String, Object>>) r.get(typeCode);
			    			 break;
			    		 }
					  }
			    	 Map<String, Object> zmap=new HashMap<String, Object>();
			    	 zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
				     //zmap.put("itemCode", o.get("itemCode").toString());
				     //zmap.put("itemValue",o.get("itemValue").toString());
				     
			    	 if(flag){
			    		 zlist.add(zmap);
			    	 }else{
					     fmap.put(typeCode, zlist);
						 result.add(fmap); 
			    	 }
			    }else{
			    	Map<String, Object> zmap=new HashMap<String, Object>();
			    	zmap.put(o.get("itemCode").toString(), o.get("itemValue").toString());
			    	//zmap.put("itemCode", o.get("itemCode").toString());
			    	//zmap.put("itemValue",o.get("itemValue").toString());
			    	zlist.add(zmap);
			    	fmap.put(typeCode, zlist);
					result.add(fmap);
			    }
			}
		}
		return result;
	}

	@Override
	public boolean updateByUuid(Map<String, Object> paramMap) {
		int result = userMapper.updateByUuid(paramMap);
		if (result > 0){
			return true;
		}
		return false;
	}

	@Override
	public User findByPhone(String phone) {
		return userMapper.findByLoginName(phone);
	}

	@Override
	public long todayCount() {
		return userMapper.todayCount();
	}

	@Override
	public void modify(String loginName) {
		Map<String,Object> map = new HashMap<>();
		map.put("loginName", loginName);
		User user = userMapper.findSelective(map);
		if (user!=null) {
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("id", user.getId());
			paramMap.put("loginTime", DateUtil.getNow());
			userMapper.updateSelective(paramMap);
		}
	}

	@Override
	public List<ChannelWarnModel> registerCountByChannel() {
		Map<String, Object> search = new HashMap<>();
		search.put("startTime", com.xiji.cashloan.core.common.util.DateUtil.getDayStartTime(new Date()));
		List<Map> result = userMapper.registerCountByChannel(search);
		List<ChannelWarnModel> reponse = new ArrayList<>();
		ChannelWarnModel model = null;
		for(int i =0 ; i < result.size(); i++) {
			if(null == model) {
				model = new ChannelWarnModel();
			}
			Map map= result.get(i);
			Long channel_id =  new Long(map.get("channel_id").toString());
			model.setChannelId(channel_id);
			if(map.get("register_client").toString().contains("wechat")) {
				model.setWechatCount(new Integer(map.get("num").toString()));
			} else if(map.get("register_client").toString().contains("qq")) {
				model.setQqCount(new Integer(map.get("num").toString()));
			} else if(map.get("register_client").toString().contains("weibo")) {
				model.setWeiboCount(new Integer(map.get("num").toString()));
			} else {
				model.setOtherCount(new Integer(map.get("num").toString()));
			}

			if(i == result.size() -1) {
				reponse.add(model);
			}else {
				Map map_next= result.get(i+1);
				Long next_channel_id =  new Long(map_next.get("channel_id").toString());
				if(null != next_channel_id
						&&  channel_id.longValue() != next_channel_id.longValue()) {
					reponse.add(model);
				}
			}

		}

		return reponse;
	}

}