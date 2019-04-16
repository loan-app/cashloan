package com.xiji.cashloan.cl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xiji.cashloan.cl.domain.*;
import com.xiji.cashloan.cl.manage.BankCardManage;
import com.xiji.cashloan.cl.mapper.*;
import com.xiji.cashloan.cl.model.*;
import com.xiji.cashloan.cl.model.pay.common.PayCommonUtil;
import com.xiji.cashloan.cl.model.pay.common.vo.request.PaymentReqVo;
import com.xiji.cashloan.cl.model.pay.common.vo.response.PaymentResponseVo;
import com.xiji.cashloan.cl.monitor.BusinessExceptionMonitor;
import com.xiji.cashloan.cl.service.*;
import com.xiji.cashloan.cl.service.impl.assist.blacklist.*;
import com.xiji.cashloan.cl.util.CreditConstant;
import com.xiji.cashloan.cl.util.OcrConstant;
import com.xiji.cashloan.cl.util.model.CarrierMxUtils;
import com.xiji.cashloan.cl.util.model.ModelUtil;
import com.xiji.cashloan.core.common.context.Constant;
import com.xiji.cashloan.core.common.context.Global;
import com.xiji.cashloan.core.common.exception.BussinessException;
import com.xiji.cashloan.core.common.exception.SimpleMessageException;
import com.xiji.cashloan.core.common.mapper.BaseMapper;
import com.xiji.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xiji.cashloan.core.common.util.DateUtil;
import com.xiji.cashloan.core.common.util.NidGenerator;
import com.xiji.cashloan.core.common.util.ShardTableUtil;
import com.xiji.cashloan.core.common.util.StringUtil;
import com.xiji.cashloan.core.domain.Borrow;
import com.xiji.cashloan.core.domain.User;
import com.xiji.cashloan.core.domain.UserBaseInfo;
import com.xiji.cashloan.core.mapper.UserBaseInfoMapper;
import com.xiji.cashloan.core.mapper.UserMapper;
import com.xiji.cashloan.core.model.BorrowModel;
import com.xiji.cashloan.core.model.UserBaseInfoModel;
import com.xiji.cashloan.core.service.UserBaseInfoService;
import com.xiji.cashloan.rc.domain.SceneBusinessLog;
import com.xiji.cashloan.rc.mapper.SceneBusinessLogMapper;
import com.xiji.cashloan.rc.mapper.SceneBusinessMapper;
import com.xiji.cashloan.rc.model.TppBusinessModel;
import com.xiji.cashloan.rc.model.TppServiceInfoModel;
import com.xiji.cashloan.rc.service.*;
import com.xiji.cashloan.rule.domain.*;
import com.xiji.cashloan.rule.mapper.*;
import com.xiji.cashloan.rule.model.ManageReviewModel;
import com.xiji.cashloan.rule.model.ManageRuleResultModel;
import com.xiji.cashloan.rule.model.srule.client.RulesExecutorUtil;
import com.xiji.cashloan.rule.model.srule.model.SimpleRule;
import com.xiji.cashloan.system.service.SysConfigService;
import com.xiji.creditrank.cr.domain.Credit;
import com.xiji.creditrank.cr.mapper.CreditMapper;
import ml.dmlc.xgboost4j.java.Booster;
import ml.dmlc.xgboost4j.java.DMatrix;
import ml.dmlc.xgboost4j.java.XGBoost;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tool.util.BigDecimalUtil;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 借款信息表ServiceImpl
 *
 * @author wnb
 * @date 2018/11/27
 * @version 1.0.0
 *
 *
 * 未经授权不得进行修改、复制、出售及商业使用
 */
@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
@Service("clBorrowService")
@Transactional(rollbackFor = Exception.class)
public class ClBorrowServiceImpl extends BaseServiceImpl<Borrow, Long> implements ClBorrowService {

	private static final Logger logger = LoggerFactory.getLogger(ClBorrowServiceImpl.class);

	@Resource
	private ClBorrowMapper clBorrowMapper;
	@Resource
	private BorrowProgressMapper borrowProgressMapper;
	@Resource
	private BorrowRepayMapper borrowRepayMapper;
	@Resource
	private CreditMapper creditMapper;
	@Resource
	private BorrowRepayLogMapper borrowRepayLogMapper;
	@Resource
	private BankCardManage bankCardManage;
	@Resource
	private UserInviteMapper userInviteMapper;
	@Resource
	private ProfitAgentMapper profitAgentMapper;
	@Resource
	private RuleEngineInfoMapper ruleEngineInfoMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private UserAuthService userAuthService;
	@Resource
	private BorrowRuleEngineMapper borrowRuleEngineMapper;
	@Resource
	private RuleEngineConfigMapper ruleEngineConfigMapper;
	@Resource
	private BorrowRuleResultMapper borrowRuleResultMapper;
	@Resource
	private RcQianchenService rcQianchenService;
	@Resource
	private QianchengReqlogMapper qianchengReqlogMapper;
	@Resource
	private UserBaseInfoMapper userBaseInfoMapper;
	@Resource
	private PayLogMapper payLogMapper;
	@Resource
	private OperatorReqLogMapper operatorReqLogMapper;
	@Resource
	private UrgeRepayOrderMapper urgeRepayOrderMapper;
	@Resource
	private RuleEngineMapper ruleEngineMapper;
	@Resource
	private ClSmsService clSmsService;
	@Resource
	private TongdunReqLogService tongdunReqLogService;
	@Resource
	private SceneBusinessLogMapper sceneBusinessLogMapper;
	@Resource
	private SceneBusinessLogService sceneBusinessLogService;
	@Resource
	private SceneBusinessMapper sceneBusinessMapper;
	@Resource
	private ZhimaService zhimaService;
	@Resource
	private SimpleBorrowCountService simpleBorrowCountService;
	@Resource
	private SimpleVoicesCountService simpleVoicesCountService;
	@Resource
	private SimpleContactCountService simpleContactCountService;
	@Resource
	private UserBaseInfoService userBaseInfoService;
	@Resource
	private TppBusinessService tppBusinessService;
	@Resource
	private BorrowRepayService borrowRepayService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private BorrowScoreResultMapper borrowScoreResultMapper;
	@Resource
	private UserBlackInfoMapper userBlackInfoMapper;
	@Resource
	private MagicRiskService magicRiskService;
	@Resource
	private XinyanRiskService xinyanRiskService;
	@Resource
	private YixinRiskService yixinRiskService;
	@Resource
	private PinganRiskService pinganRiskService;
	@Resource
	private ManualReviewOrderMapper manualReviewOrderMapper;
	@Resource
	private OperatorVoiceCntMapper operatorVoiceCntMapper;
	@Resource
	private OperatorVoiceMapper operatorVoiceMapper;
	@Resource
	private NameBlacklistMapper nameBlacklistMapper;
	@Resource
	private BorrowOperatorLogService borrowOperatorLogService;
	@Resource
	private DecisionService decisionService;
	@Resource
	private UserRemarkService userRemarkService;
	@Resource
	private YouDunRiskService youDunRiskService;
	@Resource
	private OperatorReportMapper operatorReportMapper;

	private static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	public BaseMapper<Borrow, Long> getMapper() {
		return clBorrowMapper;
	}

	@Override
	public boolean isCanBorrow(Borrow borrow,String tradePwd) {
    	
    	Long userId = borrow.getUserId();
    	User user = userMapper.findByPrimary(userId);
    	//1、校验用户是否符合借款条件
    	//1.1 校验是否有对应的用户信息
    	if(user==null || user.getId()<1){
    		throw new SimpleMessageException("找不到对应的用户信息");
    	}
    	//1.2 校验交易密码是否正确
    	if(StringUtil.isBlank(user.getTradePwd())){
	    	throw new SimpleMessageException("请先设置交易密码!");
	    }
    	if(!user.getTradePwd().equals(tradePwd)){
	    	throw new SimpleMessageException(String.valueOf(Constant.FAIL_CODE_PWD),"交易密码不正确!");
	    }
    	//1.3 校验中户是否在黑名单   废弃
	    
		//1.4 校验用户是否通过各项认证
		Map<String, Object> authMap = new HashMap<String,Object>();
		authMap.put("userId", userId);
		Map<String,Object> auth = userAuthService.getAuthState(authMap);
	    if (StringUtil.isBlank(auth)||Integer.parseInt(auth.get("qualified").toString())==0) {
	    	throw new SimpleMessageException("工作信息可能未完善，无法借款，请完善资料");
		}
	    
	    //1.5 用户是否有未完成的借款
	    List<Borrow> list = clBorrowMapper.findUserUnFinishedBorrow(userId);
		if (list.size() > 0) {
	    	throw new SimpleMessageException("有未完成借款，无法借款");
		}
	    
	    //1.7 借款天数限制
//	    int day = getAgainBorrowDays(userId);
//		if (day > 0) {
//			throw new SimpleMessageException("申请失败，您在"+day+"天后才能再次申请借款");
//		}
		
	    //1.9 借款金额格式是否正确
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		int maxDays = Integer.parseInt(days[days.length-1]);// 最大借款期限
		int minDays = Integer.parseInt(days[0]);			// 最大借款期限
		String borrowCredit = Global.getValue("borrow_credit");// 借款额度
		String[] credits = borrowCredit.split(",");
		double maxCredit = Double.parseDouble(credits[credits.length-1]);// 最大借款额度
		double minCredit = Double.parseDouble(credits[0]);		
	    Double amount = borrow.getAmount();
	    if (Math.abs(borrow.getAmount())%100!=0 || amount<minCredit||amount>maxCredit) {
			throw new SimpleMessageException("借款金额格式不正确");
		}
	    
	    //1.10 信用额度是否足够
	    Map<String,Object> creditMap = new HashMap<>();
		creditMap.put("consumerNo", borrow.getUserId());
	    Credit credit = creditMapper.findSelective(creditMap);
	    if (StringUtil.isBlank(credit) || credit.getUnuse() < borrow.getAmount()) {
	    	throw new SimpleMessageException("额度不足，无法借款");
		}
	    
	    // 1.11 判断放款上限 
//	    double borrowTotal = clBorrowMapper.borrowAmountSum();
	    double repayTotal = borrowRepayMapper.findRepayTotal();
	    double loanCeiling = Global.getDouble("loan_ceiling");
	    if(loanCeiling<0 ||( repayTotal> 0 && repayTotal >= loanCeiling) ){
	    	throw new SimpleMessageException("今日借款已达上限，请明天再来！");
	    }

	    //1.12 近6个月月均话费
//	    SimpleVoicesCount simpleVoicesCount = simpleVoicesCountService.findByUserId(userId);
//	    if(null!=simpleVoicesCount&&simpleVoicesCount.getCountTwo()<=2000){
//	    	throw new SimpleMessageException("");
//	    }
		return true;
	}
    
	@Override
	public List<RepayModel> findRepay(Map<String, Object> searchMap) {
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (repayModel != null) {
				int day = DateUtil.daysBetween(DateUtil.getNow(), repayModel.getRepayTime());
				if (day > 0) {
					repayModel.setIsPunish("false");
					repayModel.setMsg(day + "天后还款");
				} else if (day == 0) {
					repayModel.setIsPunish("false");
					repayModel.setMsg("今日还款");
				} else {
					repayModel.setIsPunish("true");
					repayModel.setMsg("已逾期" + Math.abs(day) + "天");
				}
				repayModel.setRepayTimeStr(DateUtil.dateStr8(repayModel.getRepayTime()));
			}
		}
		return modelList;
	}

	@Override
	public Page<ClBorrowModel> page(Map<String, Object> searchMap, int current,
									int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<ClBorrowModel> list = clBorrowMapper.listAll(searchMap);
		for (ClBorrowModel clBorrowModel : list) {
			clBorrowModel.setCreditTimeStr(DateUtil.dateStr(
					clBorrowModel.getCreateTime(), "yyyy-MM-dd HH:mm"));
			clBorrowModel.setStateStr(clBorrowModel.getState());
			if ("审核通过".equals(clBorrowModel.getStateStr())
					||"放款失败".equals(clBorrowModel.getStateStr())
					||"待放款审核".equals(clBorrowModel.getStateStr())
					||"放款审核通过".equals(clBorrowModel.getStateStr())) {
				clBorrowModel.setState("打款中");
				clBorrowModel.setStateStr("打款中");
			}else if ("还款中".equals(clBorrowModel.getStateStr())) {
				clBorrowModel.setState("待还款");
				clBorrowModel.setStateStr("待还款");
			}else if("放款审核不通过".equals(clBorrowModel.getStateStr())){
				clBorrowModel.setState("放款失败");
				clBorrowModel.setStateStr("放款失败");
			}
		}
		return (Page<ClBorrowModel>) list;
	}

	@Override
	public List<IndexModel> listIndex(String userId) {
		List<IndexModel> list = clBorrowMapper.listIndex();
		List indexList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String cardNo = list.get(i).getCardNo();
			if (StringUtil.isBlank(cardNo)) {
				continue;
			}
			cardNo = StringUtil.substring(cardNo, cardNo.length() - 4);
			double money = list.get(i).getAmount();
			int time = 0;
			if (list.get(i) != null && list.get(i).getCreateTime() != null
					&& list.get(i).getLoanTime() != null) {
				time = DateUtil.minuteBetween(list.get(i).getCreateTime(), list
						.get(i).getLoanTime());
			}
			String value;
			if(StringUtil.isBlank(userId) || StringUtil.equals(userId, "0")) {
				value = "尾号" + cardNo + " " + "成功借款" + 8000
						+ "元 用时" + time + "分钟";
			} else {
				value = "尾号" + cardNo + " " + "成功借款" + (int) (money)
						+ "元 用时" + time + "分钟";
			}
			Map<String, Object> map = new HashMap<>();
			map.put("id", i);
			map.put("value", value);
			indexList.add(map);
		}
		return indexList;
	}

	@Override
	public Map<String, Object> findIndex(String userId) {
		Map<String, Object> result = new HashMap<String, Object>();

		Boolean isBorrow = false;
		int count;
		User user = null;
		Credit credit = null;
		Borrow borrow = null;
		if (StringUtil.isNotBlank(userId) && !StringUtil.equals(userId, "0")) {
			user = userMapper.findByPrimary(Long.parseLong(userId));
			if(user != null){
				credit = creditMapper.findByConsumerNo(userId);
			}
		}

		double loanCeiling = Global.getDouble("loan_ceiling");   //放款上限
		double repayTotal = borrowRepayMapper.findRepayTotal(); //当前放款量
		// double repayTotal = clBorrowMapper.borrowAmountSum(); 此方法会对DB产生很大的压力，弃用
		// 计算剩余放款额度
		if (loanCeiling <= 0 || loanCeiling <= repayTotal) {
			loanCeiling = 0;
		} else {
			loanCeiling -= repayTotal;
		}

		String[] fees;
		String borrowDay;
		int maxDays = 0;
		int minDays = 0;
		String[] credits = {CreditConstant.AMOUNTS};
		String[] days = {String.valueOf(CreditConstant.MIN_DAY), String.valueOf(CreditConstant.MAX_DAY)};
		String borrowCredit;
		double maxCredit = 0d;
		double minCredit = 0d;
		if(user == null) {
			String fee = CreditConstant.FEE_POWER;
			fees = fee.split(",");
			maxDays = CreditConstant.MAX_DAY;
			minDays = CreditConstant.MIN_DAY;
			maxCredit = CreditConstant.MAX_CREDIT;
			minCredit = CreditConstant.MIN_CREDIT;
		} else {
			String fee = Global.getValue("fee");// 综合费用
			fees = fee.split(",");
			borrowDay = Global.getValue("borrow_day");// 借款天数
			days = borrowDay.split(",");
			maxDays = Integer.parseInt(days[days.length-1]);// 最大借款期限
			minDays = Integer.parseInt(days[0]);			// 最小借款期限
			borrowCredit = Global.getValue("borrow_credit");// 借款额度
			credits = borrowCredit.split(",");
			maxCredit = Double.parseDouble(credits[credits.length-1]);// 最大借款额度
			borrowCredit = getCredit(borrowCredit, credit.getUnuse());
			credits = borrowCredit.split(",");
			if(StringUtil.isBlank(credits[0])) {
				minCredit = (credit != null ? credit.getUnuse() : Double.parseDouble(Global.getValue("init_credit")));
			} else {
				minCredit = Double.parseDouble(credits[0]);				// 最小借款额度
			}
		}

		if(user != null){
			result.put("total", credit.getUnuse());
		} else {
//			result.put("total", Global.getValue("init_credit"));
			result.put("total", CreditConstant.AMOUNTS);
		}
		Map<String, Object> auth = new HashMap<String, Object>();
		auth.put("total", Global.getInt("auth_total")); // 认证总项数量
		auth.put("result", 0);
		auth.put("qualified", 0);
		result.put("cardNo", "");

		if (user != null) {
			boolean isPwd = false;
			if (StringUtil.isNotBlank(user.getTradePwd())) {
				isPwd = true;
			}
			result.put("isPwd", isPwd);

			Map<String, Object> bankParam = new HashMap<String, Object>();
			bankParam.put("userId",user.getId());
			BankCard bc = bankCardManage.findByUserId(bankParam);
			if (bc != null) {
				result.put("cardId", bc.getId());
				result.put("cardNo", bc.getCardNo());
				result.put("cardName", bc.getBank());
			}else {
				result.put("cardId", 0);
				result.put("cardName", "");
				result.put("cardNo", "");
			}
			
			count = clBorrowMapper.successCount(user.getId());
			Map<String, Object> borrowMap = new HashMap<String, Object>();
			borrowMap.put("userId", Long.parseLong(userId));
			borrow = clBorrowMapper.findRepayBorrow(borrowMap);
			
			if (borrow != null) {
				if (borrow.getState().equals(BorrowModel.STATE_REPAY)
						|| borrow.getState().equals(BorrowModel.STATE_DELAY)
					|| borrow.getState().equals(BorrowModel.STATE_BAD)
					|| borrow.getState().equals(BorrowModel.STATE_DELAY_PAY)) {
					result.put("isRepay", true);
				} else {
					result.put("isRepay", false);
				}
			}
			
			List<BorrowProgressModel> list = new ArrayList<BorrowProgressModel>();
			if (borrow != null) {
				list = borrowProgress(borrow, "index");
				result.put("list", list);
				result.put("borrowId", borrow.getId());
			}
			
			if (borrow != null && getAgainBorrowDays(borrow.getUserId()) > 0) {
				isBorrow = true;
			}

			if (list != null && !list.isEmpty()) {
				isBorrow = true;
				Map<String, Object> unRepayMap = new HashMap<String, Object>();
				List<String> stateList = Arrays.asList(BorrowModel.STATE_PRE,
						BorrowModel.STATE_AUTO_PASS,
						BorrowModel.STATE_NEED_REVIEW, BorrowModel.STATE_PASS,
						BorrowModel.STATE_REPAY);
				unRepayMap.put("userId", userId);
				unRepayMap.put("stateList", stateList);
				Borrow unRepayBorrow = clBorrowMapper.findByUserIdAndState(unRepayMap);
				if (unRepayBorrow != null) {
					borrow = unRepayBorrow;
				}
			}

			result.put("borrow", borrow);

			Map<String, Object> authMap = new HashMap<String,Object>();
			authMap.put("userId", userId);
			auth = userAuthService.getAuthState(authMap);

			if (auth != null && auth.get("qualified") != null
					&& "1".equals(auth.get("qualified").toString())) {
				
				if (credit != null && "10".equals(credit.getState())) {
					result.put("total", credit.getUnuse());
					if (credit.getTotal() - credit.getUsed() < 100) {
						minCredit = credit.getTotal() - credit.getUsed();
					}
					maxCredit = credit.getUnuse();
				}
			}
			result.put("count", count);

		}
		
		result.put("auth", auth);
		result.put("maxCredit", maxCredit);
		result.put("minCredit", minCredit);
		
		result.put("creditList", Arrays.asList(credits));
		result.put("dayList", Arrays.asList(days));
		result.put("interests", Arrays.asList(fees));
		result.put("maxDays", maxDays);
		result.put("minDays", minDays);
		result.put("isBorrow", isBorrow);
		result.put("title", Global.getValue("title")); // 平台名称
		result.put("loanCeiling", loanCeiling);
		return result;
	}
	
	/**
	 * 计算再次借款需间隔的天数
	 * @param userId
	 * @return
	 */
	private int getAgainBorrowDays(Long userId){
		int day = 0;
		String againBorrow = Global.getValue("again_borrow");
		Borrow lastBorrow = findLastBorrow(userId);
		if (StringUtil.isNotBlank(againBorrow) 
				&& lastBorrow != null 
				&& (lastBorrow.getState().equals(BorrowModel.STATE_AUTO_REFUSED) 
						|| lastBorrow.getState().equals(BorrowModel.STATE_REFUSED)
				)) {
			day = DateUtil.daysBetween(lastBorrow.getCreateTime(), DateUtil.getNow());
			day = Integer.parseInt(againBorrow) - day;
		}
		return day;
	}

	/**
	 * 借款进度显示
	 * 
	 * @param borrow
	 * @param pageFlag
	 *            detail代表详情页，index首页，首页不显示审核不通过和放款成功的进度，显示可以借款的信息
	 * @return
	 */
	public List<BorrowProgressModel> borrowProgress(Borrow borrow,
			String pageFlag) {
		List<BorrowProgressModel> list = new ArrayList<BorrowProgressModel>();
		Map<String, Object> bpMap = new HashMap<String, Object>();
		bpMap.put("borrowId", borrow.getId());
		List<BorrowProgressModel> pgList;
		int day = getAgainBorrowDays(borrow.getUserId());

		// 待审核
		if (BorrowModel.STATE_PRE.equals(borrow.getState())
				|| BorrowModel.STATE_NEED_REVIEW.equals(borrow.getState())) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			Calendar cal = Calendar.getInstance(); 
			cal.setTime(pgList.get(0).getCreateTime());
			cal.add(Calendar.SECOND, +1);
			BorrowProgressModel progress = new BorrowProgressModel();
			progress.setUserId(borrow.getUserId());
			progress.setBorrowId(borrow.getId());
			progress.setRemark("已进入风控审核状态，请耐心等待。");
			progress.setStr("审核中");
			progress.setState(progress.getStr());
			progress.setType("10");
			progress.setCreateTime(cal.getTime());
			list.add(progress);

			progress = pgList.get(0);
			progress.setStr(progress.getState());
			progress.setState(progress.getStr());
			progress.setType("10");
			list.add(progress);
		}

		// 审核不通过 （自动审核不通过，人工复审不通过）借款记录
		if ("detail".equals(pageFlag)
				&& (BorrowModel.STATE_AUTO_REFUSED.equals(borrow.getState()) || BorrowModel.STATE_REFUSED.equals(borrow.getState()))) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			
			int size = pgList.size();
			BorrowProgressModel progress = pgList.get(size - 1);
			progress.setStr(progress.getState());
			progress.setState(progress.getStr());
			progress.setType("20");
			if (day>0) {
				progress.setRemark("很遗憾,您未通过审核,请等待"+day+"天后可再次申请借款");
			}else if(BorrowModel.STATE_AUTO_REFUSED.equals(borrow.getState())){
				progress.setRemark("很遗憾，您未通过审核");
			}
			list.add(progress);

			progress = pgList.get(0);
			progress.setStr(progress.getState());
			progress.setState(progress.getStr());
			progress.setType("10");
			
			
			list.add(progress);
		}
		
		// 审核不通过 （自动审核不通过，人工复审不通过）首页
		if ("index".equals(pageFlag) && day>0 
				&& (BorrowModel.STATE_AUTO_REFUSED.equals(borrow.getState()) || BorrowModel.STATE_REFUSED
						.equals(borrow.getState()))) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			
			int size = pgList.size();
			BorrowProgressModel progress = pgList.get(size - 1);
			progress.setStr(progress.getState());
			progress.setState(progress.getStr());
			progress.setType("20");
			progress.setRemark("很遗憾,您未通过审核,请等待"+day+"天后可再次申请借款");
			list.add(progress);

			progress = pgList.get(0);
			progress.setStr(progress.getState());
			progress.setState(progress.getStr());
			progress.setType("10");
			
			
			list.add(progress);
		}

		// 打款中（放款失败）
		if (BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())
				|| BorrowModel.STATE_REPAY_FAIL.equals(borrow.getState())
				|| BorrowModel.WAIT_AUDIT_LOAN.equals(borrow.getState())
				|| BorrowModel.AUDIT_LOAN_PASS.equals(borrow.getState())
				|| BorrowModel.STATE_AUTO_PASS.equals(borrow.getState())
				|| BorrowModel.STATE_PASS.equals(borrow.getState())) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag
						&& (BorrowProgressModel.PROGRESS_AUTO_PASS.equals(progress.getState()) 
								|| BorrowProgressModel.PROGRESS_PERSON_PASS.equals(progress.getState())
								)) {
					
					Calendar cal = Calendar.getInstance();
					cal.setTime(progress.getCreateTime());
					cal.add(Calendar.SECOND, +1);
					BorrowProgressModel progress2 = new BorrowProgressModel();
					progress2.setUserId(Long.valueOf(borrow.getUserId()));
					progress2.setBorrowId(borrow.getId());
					progress2.setStr("打款中");
					progress2.setState("打款中");
					progress2.setMsg("打款中，请查看您的提现银行卡");
					progress2.setRemark("打款中，请查看您的提现银行卡");
					progress2.setType("10");
					progress2.setCreateTime(cal.getTime());
					list.add(progress2);
					
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					
				}

				if (BorrowProgressModel.PROGRESS_LOAN_FAIL.equals(progress
						.getState())) {
					progress.setMsg("打款中，请查看您的提现银行卡。");
					
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("borroId", progress.getBorrowId());
					params.put("type", PayLogModel.TYPE_PAYMENT);
					params.put("scenes", PayLogModel.SCENES_LOANS);
					PayLog payLog = payLogMapper.findLatestOne(params);
					if(payLog != null){
						String changeBankCardCode = Global.getValue("lianlian_change_bank_card_code");
						String changeBankCardRemark = Global.getValue("lianlian_change_bank_card_remark");
						String payLogCode = payLog.getCode();
						String payLogRemark = payLog.getRemark();
						if(StringUtil.isNotBlank(payLogCode) 
								&& StringUtil.isNotBlank(changeBankCardCode)
								&& changeBankCardCode.contains(payLogCode)){
							logger.warn("userId:" + progress.getUserId() + "， payLogCode:"+ payLogCode +" ，因银行卡原因打款失败，需要更换银行卡");
							progress.setMsg("因银行卡原因打款失败，请更换您的银行卡");
						}else if (StringUtil.isNotBlank(payLogRemark) 
								&& StringUtil.isNotBlank(changeBankCardRemark)
								&& payLogRemark.contains(changeBankCardRemark)){
							logger.warn("userId:" + progress.getUserId() + "， payLogRemark:"+ payLogRemark +" ，因银行卡原因打款失败，需要更换银行卡");
							progress.setMsg("因银行卡原因打款失败，请更换您的银行卡");
						}
					}
					
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}
			}
		}

		// 待还款（放款成功）
		if (BorrowModel.STATE_REPAY.equals(borrow.getState())) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag && (BorrowProgressModel.PROGRESS_AUTO_PASS
								.equals(progress.getState()) || BorrowProgressModel.PROGRESS_PERSON_PASS
								.equals(progress.getState()))) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					passFlag = false;
				}

				if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress.getState())) {
					double repayAmount = borrow.getRealAmount() + borrow.getFee();

					Calendar cal = Calendar.getInstance();
					cal.setTime(progress.getCreateTime());
					cal.add(Calendar.SECOND, +1);
					progress = new BorrowProgressModel();
					progress.setUserId(Long.valueOf(borrow.getUserId()));
					progress.setBorrowId(borrow.getId());
					progress.setStr("待还款");
					progress.setRemark("您需要在" + borrow.getTimeLimit() + "天后还款" + repayAmount + "元");
					Map<String,Object> paramMap = new HashMap<>();
					paramMap.put("borrowId", borrow.getId());
					BorrowRepay repay = borrowRepayMapper.findByBorrowIdState(paramMap);
					if (repay!=null) {
						day = DateUtil.daysBetween(new Date(),
								repay.getRepayTime());
						if (day > 0) {
							progress.setRemark("您需要在" + day + "天后还款" + repay.getAmount()+repay.getPenaltyAmout() + "元");
						} else if (day == 0) {
							progress.setRemark("您需要在今天还款" + repay.getAmount()+repay.getPenaltyAmout() + "元");
						}
					}
					
					if ("1".equals(borrow.getTimeLimit())) {
						progress.setRemark("您需要在今天还款" + repayAmount+ "元");
					}
					progress.setState("待还款");
					progress.setType("10");
					progress.setCreateTime(cal.getTime());
					list.add(progress);

					progress = pgList.get(i);
					progress.setStr("已打款");
					progress.setState(progress.getStr());
					list.add(progress);
				}
			}
		}
		
		//还款处理中
		if (borrow.getState().equals(BorrowModel.STATE_REPAY_PROCESSING)) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag
						&& (BorrowProgressModel.PROGRESS_AUTO_PASS
								.equals(progress.getState()) || BorrowProgressModel.PROGRESS_PERSON_PASS
								.equals(progress.getState()))) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					passFlag = false;
				}
				
				if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress.getState())) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}
				
				if (BorrowProgressModel.PROGRESS_REPAY_PROCESSING.equals(progress.getState())) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

			}
		}

		// 还款成功
		if ("detail".equals(pageFlag)
				&& (BorrowModel.STATE_FINISH.equals(borrow.getState()) || BorrowModel.STATE_REMISSION_FINISH
						.equals(borrow.getState()))) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag
						&& (BorrowProgressModel.PROGRESS_AUTO_PASS
								.equals(progress.getState()) || BorrowProgressModel.PROGRESS_PERSON_PASS
								.equals(progress.getState()))) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					passFlag = false;
				}

				if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress
						.getState())
						|| BorrowProgressModel.PROGRESS_REPAY_SUCCESS
								.equals(progress.getState())
						|| BorrowProgressModel.PROGRESS_REPAY_REMISSION_SUCCESS
								.equals(progress.getState())) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}
			}
		}
		// 展期成功
		if (BorrowModel.STATE_DELAY_PAY.equals(borrow.getState())) {
			bpMap.put("state", borrow.getState());
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag
					&& (BorrowProgressModel.PROGRESS_AUTO_PASS
					.equals(progress.getState()) || BorrowProgressModel.PROGRESS_PERSON_PASS
					.equals(progress.getState()))) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					passFlag = false;
				}

				if (BorrowProgressModel.PROGRESS_REPAY_DELAY_PAY.equals(progress
					.getState())) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}
			}
		}
		// 逾期
		int signState = Integer.parseInt(BorrowModel.STATE_DELAY);
		if (BorrowModel.STATE_DELAY.equals(borrow.getState())
				|| BorrowModel.STATE_BAD.equals(borrow.getState())) {
			bpMap.put("state", BorrowModel.STATE_BAD);
			pgList = borrowProgressMapper.listProgress(bpMap);
			boolean passFlag = true;
			boolean overdueFlag = true;

			for (int i = pgList.size() - 1; i >= 0; i--) {
				BorrowProgressModel progress = pgList.get(i);
				progress.setType("10");
				int prState = Integer.parseInt(progress.getState());
				if (i == 0) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (passFlag
						&& (BorrowProgressModel.PROGRESS_AUTO_PASS
								.equals(progress.getState()) || BorrowProgressModel.PROGRESS_PERSON_PASS
								.equals(progress.getState()))) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
					passFlag = false;
				}

				if (BorrowProgressModel.PROGRESS_LOAN_SUCCESS.equals(progress
						.getState())) {
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());
					list.add(progress);
				}

				if (overdueFlag && prState > signState) {
					progress = pgList.get(pgList.size() - 1);
					progress.setStr(progress.getState());
					progress.setState(progress.getStr());

					Calendar cal = Calendar.getInstance();
					cal.setTime(progress.getCreateTime());
					cal.add(Calendar.SECOND, +1);
					progress.setRemark("您已逾期,请尽快还款");
					progress.setState("已逾期");
					progress.setType("10");
					progress.setCreateTime(cal.getTime());
					list.add(progress);
					overdueFlag = false;
				}
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> choice(double amount, String timeLimit, String userId) {
		double fee = 0;
		if(StringUtil.isBlank(userId) || StringUtil.equals(userId, "0")) {
			fee = CreditConstant.FEE;
		} else {
			String fee_ = Global.getValue("fee");// 综合费用
			String[] fees = fee_.split(",");
			String borrowDay = Global.getValue("borrow_day");// 借款天数
			String[] days = borrowDay.split(",");
			for (int i = 0; i < days.length; i++) {
				if (timeLimit.equals(days[i])) {
					fee = BigDecimalUtil.round(BigDecimalUtil.mul(amount, Double.parseDouble(fees[i])));
				}
			}
		}
		Map<String,Object> map = new HashMap<>();
		map.put("fee", BigDecimalUtil.decimal(fee, 2));
		String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
		if ("10".equals(beheadFee)) {// 启用
			map.put("realAmount", amount - fee);
		} else {
			map.put("realAmount", amount);
		}
		Map<String, Object> feeMap = getFeeMap(fee);
		map.put("feeDetail", feeMap);

		return map;
	}
	
	@Override
	public List<Map<String, Object>> choices(String userId) {
		String fee_ = Global.getValue("fee");// 综合费用
		String feeName = sysConfigService.findByCode("fee").getName();// 综合费用
		String[] fees = fee_.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		String borrowCredit = Global.getValue("borrow_credit");//借款金额
		String[] borrowCredits = borrowCredit.split(",");

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		if(StringUtil.isBlank(userId) || StringUtil.equals(userId, "0")) {
			list = getNoUserChoices(feeName);
		} else {
			for (int i = 0; i < days.length; i++) {
				for (int j = 0; j < borrowCredits.length; j++) {
					Map<String,Object> map = new HashMap<>();
					double fee = Double.parseDouble(borrowCredits[j])*Double.parseDouble(fees[i]);
					map.put("fee", BigDecimalUtil.decimal(fee, 2));
					map.put("feeName", feeName);

					double amount = Double.parseDouble(borrowCredits[j]);
					String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
					if ("10".equals(beheadFee)) {// 启用
						map.put("realAmount", amount - fee);
					} else {
						map.put("realAmount", amount);
					}

					Map<String, Object> feeDetail= new HashMap<String, Object>();
					List<Map<String,Object>> feeDetailList = new ArrayList<>();
					double total = 0;
					Map<String,Object> feeMap=getFeeMap(fee);
					feeDetail.put("title", feeMap.get("serviceFeeName"));
					feeDetail.put("value", feeMap.get("serviceFee"));
					feeDetailList.add(feeDetail);
					feeDetail= new HashMap<String, Object>();
					feeDetail.put("title", feeMap.get("infoAuthFeeName"));
					feeDetail.put("value", feeMap.get("infoAuthFee"));
					feeDetailList.add(feeDetail);
					feeDetail= new HashMap<String, Object>();
					feeDetail.put("title", feeMap.get("interestName"));
					feeDetail.put("value", feeMap.get("interest"));
					feeDetailList.add(feeDetail);
					map.put("feeDetailList", feeDetailList);

					map.put("timeLimit", days[i]);
					map.put("amount", amount);

					//IOS端返回数据
					map.put("feeDetail", feeMap);
					list.add(map);
				}
			}
		}

		return list;
	}

	@Override
	public Borrow saveBorrow(Borrow borrow) {
		String fee_ = Global.getValue("fee");// 综合费用
		String[] fees = fee_.split(",");
		String borrowDay = Global.getValue("borrow_day");// 借款天数
		String[] days = borrowDay.split(",");
		
		String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
		double fee = 0;
		for (int i = 0; i < days.length; i++) {
			if (borrow.getTimeLimit().equals(days[i])) {
				fee = BigDecimalUtil.round(BigDecimalUtil.mul(borrow.getAmount(), Double.parseDouble(fees[i])));
				borrow.setFee(fee);
				if ("10".equals(beheadFee)) {
					borrow.setRealAmount(borrow.getAmount() - fee);
				} else {
					borrow.setRealAmount(borrow.getAmount());
				}
			}
		}

		/*if (StringUtil.equals("dev", Global.getValue("app_environment"))
				&&StringUtil.equals("1", borrow.getTimeLimit())) {
			fee = BigDecimalUtil.round(BigDecimalUtil.mul(borrow.getAmount(), 0.01));
			borrow.setFee(fee);
			if (beheadFee.equals("10")) {
				borrow.setRealAmount(borrow.getAmount() - fee);
			} else {
				borrow.setRealAmount(borrow.getAmount());
			}
		}*/
		Map<String,Object> feeMap=getFeeMap(fee);
		double serviceFee = Double.valueOf(String.valueOf(feeMap.get("serviceFee")));// 居间服务费
		double infoAuthFee = Double.valueOf(String.valueOf(feeMap.get("infoAuthFee")));// 信息认证费
		double interest = Double.valueOf(String.valueOf(feeMap.get("interest")));// 利息
		
		borrow.setInfoAuthFee(infoAuthFee);
		borrow.setServiceFee(serviceFee);
		borrow.setInterest(interest);
		
		borrow.setOrderNo(NidGenerator.getOrderNo());
		borrow.setState(BorrowModel.STATE_PRE);
		borrow.setCreateTime(DateUtil.getNow());
		borrow.setIsOverdue("10");
		
		// 首再贷标识标识
		int finishCount = clBorrowMapper.finishCount(borrow.getUserId()); // 借款完成次数
		if (finishCount > 0) {
			borrow.setAgain("20");
		} else {
			borrow.setAgain("10");
		}
		
		clBorrowMapper.save(borrow);
		return borrow;
	}

	@Override
	public List<Borrow> findBorrowByMap(Map<String, Object> searchMap) {
		List<Borrow> list = clBorrowMapper.listSelective(searchMap);
		return list;
	}

	private void addList(BorrowProgressModel bpModel) {
		if (bpModel.getState().equals(BorrowModel.STATE_PRE)
				|| bpModel.getState().equals(BorrowModel.STATE_NEED_REVIEW)) {
			bpModel.setMsg("系统审核中,请耐心等待");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_PASS)
				|| bpModel.getState().equals(BorrowModel.STATE_PASS)) {
			bpModel.setMsg("恭喜通过风控审核");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
				|| bpModel.getState().equals(BorrowModel.STATE_REFUSED)) {
			bpModel.setMsg("很遗憾,您未通过审核");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_REPAY)
				|| bpModel.getState().equals(BorrowModel.STATE_REPAY_FAIL)) {
			bpModel.setMsg("打款中,请注意查收短信");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_FINISH)
				|| bpModel.getState()
						.equals(BorrowModel.STATE_REMISSION_FINISH)) {
			bpModel.setMsg("已还款");
			bpModel.setType("30");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_DELAY)) {
			bpModel.setMsg("已逾期,请尽快还款");
			bpModel.setType("20");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_DELAY_PAY)) {
			bpModel.setMsg("恭喜展期成功");
			bpModel.setType("10");
		}
		if (bpModel.getState().equals(BorrowModel.STATE_BAD)) {
			bpModel.setMsg("已坏账");
			bpModel.setType("20");
		}
	}

	@Override
	public Page<ManageBorrowModel> listModel(Map<String, Object> params,
			int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = clBorrowMapper.listModel(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public int updateSelective(Map<String, Object> data) {
		return clBorrowMapper.updateSelective(data);
	}

	public void saveQcResult(String qcRsMsg, Borrow borrow){
		//处理浅橙接口返回结果
    	if(StringUtil.isNotBlank(qcRsMsg)){
			JSONObject resultJson = null;
			if (!qcRsMsg.toUpperCase().startsWith("<HTML>")) {
				resultJson = JSONObject.parseObject(qcRsMsg);
			}
			String code = resultJson == null ? "" : resultJson.getString("code");
			String message = resultJson == null ? (qcRsMsg) : (String.valueOf(resultJson.get("message")));

			QianchengReqlog reqLog = qianchengReqlogMapper.findByBorrowId(borrow.getId());
			reqLog.setRespCode(code);
			reqLog.setRespParams(qcRsMsg);
			reqLog.setRespTime(DateUtil.getNow());

			if ("200".equals(code) && resultJson != null) {
				reqLog.setRespOrderNo(resultJson.getString("orderNo"));
				qianchengReqlogMapper.update(reqLog);
			} else {
				BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_4, reqLog.getOrderNo(), message);
				reqLog.setRsState("1");
				reqLog.setRsDesc(message);
				qianchengReqlogMapper.update(reqLog);
				syncSceneBusinessLog("1", "成功", borrow.getId(), TppBusinessModel.BUS_NID_QCRISK);
			}
		} else {
			syncSceneBusinessLog("0", "浅橙返回为空字符串", borrow.getId(), TppBusinessModel.BUS_NID_QCRISK);
		}
	}


	/**
	 * 修改标的状态
	 * 
	 * @param preState
	 * @param state
	 */
	@Override
	public int modifyState(long id, String state,String preState) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", state);
		paramMap.put("id", id);
		paramMap.put("preState",preState);
		return clBorrowMapper.updateFromPreState(paramMap);
	}

	/**
	 * 添加借款进度
	 * 
	 * @param borrow
	 * @param state
	 */
	@Override
	public void savePressState(Borrow borrow, String state,String remark) {
		BorrowProgress borrowProgress = new BorrowProgress();
		borrowProgress.setBorrowId(borrow.getId());
		borrowProgress.setUserId(borrow.getUserId());
		if (state.equals(BorrowModel.STATE_PRE)) {
			borrowProgress.setRemark("借款"
					+ StringUtil.isNull(borrow.getAmount())
					+ "元，期限"
					+ borrow.getTimeLimit()
					+ "天，综合费用"
					+ StringUtil.isNull(borrow.getFee()) + "元，"
					+ BorrowModel.convertBorrowRemark(state));
		} if(state.equals(BorrowModel.STATE_AUTO_REFUSED)) {
			borrowProgress.setRemark(remark);
		} else {
			borrowProgress.setRemark(BorrowModel.convertBorrowRemark(state));
		}
		borrowProgress.setState(state);
		borrowProgress.setCreateTime(DateUtil.getNow());
		borrowProgressMapper.save(borrowProgress);
	}

	/**
	 * 信用额度修改
	 * 
	 * @param amount
	 * @param type
	 */
	@Override
	public int modifyCredit(Long userId, double amount, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> creditMap = new HashMap<String, Object>();
		creditMap.put("consumerNo", userId);
		Credit credit = creditMapper.findSelective(creditMap);
		if (credit != null) {
			params.put("id", credit.getId());
			if ("used".equals(type)) {
				params.put("used", amount);
				params.put("unuse", - amount);
			} else {
				params.put("used", - amount);
				if(amount + credit.getUnuse() > credit.getTotal()){
					params.put("unuse", credit.getTotal() - credit.getUsed() + amount);
				} else {
					params.put("unuse", amount);
				}
			}
			int result = creditMapper.updateAmount(params);
			
			if(result != 1){
				logger.error("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
				throw new BussinessException("更新额度失败");
			}
			return result;
		} else {
			logger.error("更新额度失败，不能出现负值，type：" + type + ",userId:" + userId);
			throw new BussinessException("更新额度失败");
		}
	}
 
//	/**
//	 * 借款标放款
//	 *
//	 * @param borrow
//	 * @param date
//	 * @param amount
//	 * @param timeLimit
//	 * @return
//	 */
//	@Override
//	public void borrowLoan(final Borrow borrow, final Date date) {
//
//		// 调用连连支付实时付款进行放款
//		new Thread() {
//			@Override
//			public void run() {
//				Map<String, Object> bankCardMap = new HashMap<String, Object>();
//				bankCardMap.put("userId", borrow.getUserId());
//				BankCard bankCard = bankCardManage.findSelective(bankCardMap);
//
//				UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
//				String orderNo = OrderNoUtil.getSerialNumber();
//				PaymentModel payment = new PaymentModel(orderNo);
//				payment.setDt_order(DateUtil.dateStr3(date));
//				if ("dev".equals(Global.getValue("app_environment"))) {
//					payment.setMoney_order("0.01");
//				} else {
//					payment.setMoney_order(StringUtil.isNull(borrow.getRealAmount()));
//				}
//				payment.setAmount(borrow.getRealAmount());
//				payment.setCard_no(bankCard.getCardNo());
//				payment.setAcct_name(baseInfo.getRealName());
//				payment.setInfo_order(borrow.getOrderNo() + "付款");
//				payment.setMemo(borrow.getOrderNo() + "付款");
//				payment.setNotify_url(Global.getValue("server_host") + "/pay/lianlian/paymentNotify.htm");
//				LianLianHelper helper = new LianLianHelper();
//				payment = (PaymentModel) helper.payment(payment);
//
//				PayLog payLog = new PayLog();
//				payLog.setOrderNo(payment.getNo_order());
//				payLog.setUserId(borrow.getUserId());
//				payLog.setBorrowId(borrow.getId());
//				payLog.setAmount(payment.getAmount());
//				payLog.setCardNo(bankCard.getCardNo());
//				payLog.setBank(bankCard.getBank());
//				payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
//				payLog.setType(PayLogModel.TYPE_PAYMENT);
//				payLog.setScenes(PayLogModel.SCENES_LOANS);
//
//				if (payment.checkReturn()) { // 已生成连连支付单，付款处理中（交易成功，不是指付款成功，是指流程正常）
//					payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
//				} else if ("4002".equals(payment.getRet_code())
//						|| "4003".equals(payment.getRet_code())
//						|| "4004".equals(payment.getRet_code())) { // 疑似重复订单，待人工审核
//					payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
//					payLog.setConfirmCode(payment.getConfirm_code());
//					payLog.setUpdateTime(DateUtil.getNow());
//				} else if ("4006".equals(payment.getRet_code()) // 敏感信息加密异常
//						|| "4007".equals(payment.getRet_code()) // 敏感信息解密异常
//						|| "4009".equals(payment.getRet_code())) { // 验证码异常
//					payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
//				} else {
//					BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_9, payLog.getOrderNo(), payment.getRet_msg());
//					payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
//					payLog.setUpdateTime(DateUtil.getNow());
//
//					clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow.getId());
//				}
//
//				payLog.setRemark(payment.getRet_msg());
//				payLog.setPayReqTime(date);
//				payLog.setCreateTime(DateUtil.getNow());
//				payLogMapper.save(payLog);
//				//clSmsService.loanInform(borrow.getUserId(), borrow.getId());
//				//demo环境下连连支付不开启状态，直接模拟连连回调成功
//				String lianlianSwitch = Global.getValue("lianlian_switch");
//				if ("dev".equals(Global.getValue("app_environment"))&&StringUtil.isNotBlank(lianlianSwitch) && "2".equals(lianlianSwitch)) {
//					if(PayLogModel.SCENES_LOANS.equals(payLog.getScenes())){
//						logger.info("模拟连连放款回调成功，生成借款计划。。");
//						// 修改借款状态
//						Map<String, Object> map = new HashMap<>();
//						map.put("id", payLog.getBorrowId());
//						map.put("state", BorrowModel.STATE_REPAY);
//						clBorrowMapper.updatePayState(map);
//
//						// 放款进度添加
//						BorrowProgress bp = new BorrowProgress();
//						bp.setUserId(payLog.getUserId());
//						bp.setBorrowId(payLog.getBorrowId());
//						bp.setState(BorrowModel.STATE_REPAY);
//						bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
//						bp.setCreateTime(DateUtil.getNow());
//						borrowProgressMapper.save(bp);
//
//						Borrow borrow = clBorrowMapper.findByPrimary(payLog.getBorrowId());
//
//						// 生成还款计划并授权
//						borrowRepayService.genRepayPlan(borrow);
//						// 更新订单状态
//						Map<String,Object> paramMap = new HashMap<String, Object>();
//						paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
//						paramMap.put("updateTime",DateUtil.getNow());
//						paramMap.put("id",payLog.getId());
//						payLogMapper.updateSelective(paramMap);
//
//					}
//				}
//			}
//		}.start();
//	}
	public boolean judge(long borrowId) {
		Map<String,Object> map = new HashMap<>();
		map.put("borrowId", borrowId);
		map.put("scenes", PayLogModel.SCENES_LOANS);
		List<PayLog> plist = payLogMapper.listSelective(map);
		boolean flag = true;
		for (PayLog payLog : plist) {
			if (PayLogModel.STATE_PAYMENT_WAIT.equals(payLog.getState())
				|| PayLogModel.STATE_PENDING_REVIEW.equals(payLog.getState())
				|| PayLogModel.STATE_PAYMENT_SUCCESS.equals(payLog.getState())) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	/**
	 * 借款标放款
	 *
	 * @param borrow
	 * @param date
	 * @return
	 */
	@Override
	public void borrowLoan(final Borrow borrow, final Date date) {

		// 调用连连支付实时付款进行放款
		new Thread() {
			@Override
			public void run() {
				Map<String, Object> bankCardMap = new HashMap<String, Object>();
				bankCardMap.put("userId", borrow.getUserId());
				BankCard bankCard = bankCardManage.findSelective(bankCardMap);

				UserBaseInfo baseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());

				boolean flag  = judge(borrow.getId());
				if(!flag){
					logger.error("放款支付终止，存在待支付或者待审核状态或者支付成功的支付记录，借款id："+borrow.getId());
					clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow.getId());
					return;
				}
				if(bankCard == null){
					logger.error("放款支付终止，绑卡信息为空，请通知用户重新绑卡："+borrow.getId());
					clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow.getId());
					return;
				}
				PaymentReqVo vo = new PaymentReqVo();
				if ("dev".equals(Global.getValue("app_environment"))) {
					vo.setAmount(3.0);
				} else {
					vo.setAmount(borrow.getRealAmount());
				}
				vo.setBankCardName(baseInfo.getRealName());
				vo.setBankCardNo(bankCard.getCardNo());
				vo.setBorrowId(borrow.getId());
				vo.setBorrowOrderNo(borrow.getOrderNo());
				vo.setMobile(bankCard.getPhone());
				vo.setShareKey(bankCard.getUserId());
				PaymentResponseVo result = PayCommonUtil.payment(vo);
				PayLog payLog = new PayLog();
				payLog.setOrderNo(result.getOrderNo());
				payLog.setUserId(borrow.getUserId());
				payLog.setBorrowId(borrow.getId());
				payLog.setAmount(borrow.getRealAmount());
				payLog.setCardNo(bankCard.getCardNo());
				payLog.setBank(bankCard.getBank());
				payLog.setSource(PayLogModel.SOURCE_FUNDS_OWN);
				payLog.setType(PayLogModel.TYPE_PAYMENT);
				payLog.setScenes(PayLogModel.SCENES_LOANS);

				if (PayCommonUtil.success(result.getStatus())) { //受理成功
					payLog.setState(PayLogModel.STATE_PAYMENT_WAIT);
				} else if (PayCommonUtil.needCheck(result.getStatus())) { // 疑似重复订单，待人工审核
					payLog.setState(PayLogModel.STATE_PENDING_REVIEW);
//					payLog.setConfirmCode(payment.getConfirm_code());
					payLog.setUpdateTime(DateUtil.getNow());
				} else {
					BusinessExceptionMonitor.add(BusinessExceptionMonitor.TYPE_11, payLog.getOrderNo(), result.getMessage());
					payLog.setState(PayLogModel.STATE_PAYMENT_FAILED);
					payLog.setUpdateTime(DateUtil.getNow());

					clBorrowMapper.updateState(BorrowModel.STATE_REPAY_FAIL ,borrow.getId());
				}
				payLog.setCode(result.getStatusCode());
				payLog.setRemark(result.getMessage());
				payLog.setPayReqTime(date);
				payLog.setCreateTime(DateUtil.getNow());
				payLogMapper.save(payLog);
				//clSmsService.loanInform(borrow.getUserId(), borrow.getId());
				//demo环境下连连支付不开启状态，直接模拟连连回调成功
				String lianlianSwitch = Global.getValue("fuiou_switch");
				if ("dev".equals(Global.getValue("app_environment"))&&StringUtil.isNotBlank(lianlianSwitch) && "2".equals(lianlianSwitch)) {
					if(PayLogModel.SCENES_LOANS.equals(payLog.getScenes())){
						logger.info("模拟连连放款回调成功，生成借款计划。。");
						// 修改借款状态
						Map<String, Object> map = new HashMap<>();
						map.put("id", payLog.getBorrowId());
						map.put("state", BorrowModel.STATE_REPAY);
						clBorrowMapper.updatePayState(map);

						// 放款进度添加
						BorrowProgress bp = new BorrowProgress();
						bp.setUserId(payLog.getUserId());
						bp.setBorrowId(payLog.getBorrowId());
						bp.setState(BorrowModel.STATE_REPAY);
						bp.setRemark(BorrowModel.convertBorrowRemark(bp.getState()));
						bp.setCreateTime(DateUtil.getNow());
						borrowProgressMapper.save(bp);

						Borrow borrow = clBorrowMapper.findByPrimary(payLog.getBorrowId());

						// 生成还款计划并授权
						borrowRepayService.genRepayPlan(borrow);
						// 更新订单状态
						Map<String,Object> paramMap = new HashMap<String, Object>();
						paramMap.put("state", PayLogModel.STATE_PAYMENT_SUCCESS);
						paramMap.put("updateTime",DateUtil.getNow());
						paramMap.put("id",payLog.getId());
						payLogMapper.updateSelective(paramMap);

					}
				}
			}
		}.start();
	}

	@Override
	public Map<String, Object> findResult(long borrowId) {
		Map<String, Object> data = new HashMap<>();
		List<ManageReviewModel> ruleList = borrowRuleResultMapper.findRuleResult(borrowId);
		data.put("ruleList", ruleList);
		List resultList = new ArrayList<>();
		List<ManageRuleResultModel> result = borrowRuleResultMapper.findResult(borrowId);
		for (ManageRuleResultModel model : result) {
			Map<String, Object> search = new HashMap<>();
			search.put("ruleId", model.getRuleId());
			search.put("borrowId", borrowId);
			List<BorrowRuleResult> infoList = borrowRuleResultMapper.findRule(search);
			for (BorrowRuleResult borrowRuleResult : infoList) {
				borrowRuleResult.setResultType(borrowRuleResult.alterType(borrowRuleResult.getResultType()));
			}
			model.setInfoList(infoList);
		}
		resultList.add(result);
		data.put("resultList", resultList);
		return data;
	}

	@Override
	public List<ManageBorrowTestModel> seleteUser() {
		List<ManageBorrowTestModel> list = clBorrowMapper.seleteUser();
		List<ManageBorrowTestModel> userList = new ArrayList<>();
		for (ManageBorrowTestModel user : list) {
			boolean type = true;
			Map<String, Object> searchMap = new HashMap<>();
			searchMap.put("userId", user.getUserId());
			List<Borrow> borrowList = clBorrowMapper.listSelective(searchMap);
			for (Borrow borrow : borrowList) {
				if (!borrow.getState().equals(BorrowModel.STATE_AUTO_REFUSED)
						& !borrow.getState().equals(BorrowModel.STATE_REFUSED)
						& !borrow.getState().equals(BorrowModel.STATE_FINISH)
						& !borrow.getState().equals(
								BorrowModel.STATE_REMISSION_FINISH)) {
					type = false;
				}
			}
			if (type) {
				userList.add(user);
			}
			if (userList.size() >= 20){
				break;
			}
		}
		return userList;
	}

	/**
	 * 人工复审
	 */
	@Override
	public int manualVerifyBorrow(Long borrowId, String state, String remark, Long userId,Boolean isBlack,Double amount) {
		int code = 0;
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);

		if (borrow != null) {
			if(!borrow.getState().equals(BorrowModel.STATE_NEED_REVIEW)){
				logger.error("人工复审失败，当前状态不是待人工复审");
				throw new BussinessException("复审失败，当前状态不是待人工复审");
			}
			Map<String,Object> map = new HashMap<String, Object>();
            Double fee = Double.parseDouble(Global.getValue("fee"));
			map.put("id", borrowId);
			map.put("state", state);   
			map.put("remark", remark);
			map.put("amount", amount);
			if(BorrowModel.STATE_REFUSED.equals(state)&&!borrow.getAmount().equals(amount)){
				throw new BussinessException("当前状态为人工复审拒绝，订单无法修改金额");
			}else {
				String beheadFee = Global.getValue("behead_fee");// 是否启用砍头息
				if ("10".equals(beheadFee)) {//启用
					map.put("realAmount", amount * (1 - fee));
				}else {
					map.put("realAmount", amount);
				}
				map.put("fee", amount * fee);
				map.put("serviceFee", amount * fee * 0.5);
				map.put("infoAuthFee", amount * fee * 0.4);
				map.put("interest", amount * fee * 0.1);
				Map<String, Object> creditMap = new HashMap<String, Object>();
				creditMap.put("consumerNo", borrow.getUserId());
				Credit credit = creditMapper.findSelective(creditMap);
				if (credit != null) {
					if (amount >= 0 && amount <= credit.getTotal()) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("consumerNo", borrow.getUserId());
						params.put("used", amount);
						params.put("unuse", credit.getTotal() - amount);
						int result = creditMapper.updateTotal(params);
						if (result != 1) {
							throw new BussinessException("修改借款额度失败");
						}
						code = clBorrowMapper.reviewState(map);
						if (code != 1) {
							throw new BussinessException("人工复审失败");
						}
					} else if (amount > credit.getTotal()) {
						Map<String, Object> params = new HashMap<String, Object>();
						params.put("consumerNo", borrow.getUserId());
						params.put("total", credit.getTotal() + (amount - credit.getTotal()));
						params.put("used", amount);
						params.put("unuse", credit.getTotal() + (amount - credit.getTotal()) - amount);
						int result = creditMapper.updateTotal(params);
						if (result != 1) {
							throw new BussinessException("修改借款额度失败");
						}
						code = clBorrowMapper.reviewState(map);
						if (code != 1) {
							throw new BussinessException("人工复审失败");
						}
					} else {
						throw new BussinessException("借款金额不能为负数");
					}
				}
			}
			savePressState(borrow, state,"");
			//更新审核表
			map.put("userId", userId);
			map.put("reviewTime", DateUtil.getNow());
			map.put("state", BorrowModel.STATE_PASS.equals(state) ? ManualReviewOrderModel.STATE_ORDER_PASS : ManualReviewOrderModel.STATE_ORDER_REFUSED);
			manualReviewOrderMapper.reviewState(map);

			if(StringUtil.isNotBlank(remark)) {
				UserRemark userRemark = new UserRemark();
				userRemark.setCreateTime(new Date());
 				userRemark.setOperateTime(new Date());
				userRemark.setRemark(remark);
				userRemark.setOperateId(userId);
				userRemark.setUserId(borrow.getUserId());
				userRemarkService.insert(userRemark);
			}

			if (BorrowModel.STATE_REFUSED.equals(state)|| BorrowModel.STATE_AUTO_REFUSED.equals(state)) {
				// 审核不通过返回信用额度
				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
				// 将用户加入黑名单
				if (isBlack){
					this.joinBlackList(borrow.getUserId());
				}
			}
			// 人工复审成功 放款
			if (BorrowModel.STATE_PASS.equals(state)) {
				if (!"10".equals(Global.getValue("manual_loan")))  { //系统配置的是否放款审核
					borrowLoan(borrow, new Date());
				}else {
					//到待放款审核状态	
					int result = modifyState(borrow.getId(), BorrowModel.WAIT_AUDIT_LOAN,BorrowModel.STATE_PASS);
					logger.info("人工复审通过 待放款审核状态result: "+result);
					if(result == 1){
						savePressState(borrow, BorrowModel.WAIT_AUDIT_LOAN,"");
					}
				}
			}
		} else {
			logger.error("复审失败，当前标不存在");
			throw new BussinessException("复审失败，当前标不存在");
		}
		return code;
	}

	/**
	 * 将用户加入黑名单
	 * @param userId
	 */
	private void joinBlackList(Long userId){
		UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(userId);
		if (userBaseInfo == null){
			return;
		}

		// 将用户信息状态为黑名单
		List list = new ArrayList();
		list.add(userId);
		int countUpdateBlack = userBaseInfoMapper.updateBlackIdNos(list);
		if (countUpdateBlack != 1){
			throw new BussinessException("更新用户信息状态为黑名单失败 userId ==>"+userId);
		}
		Date date = new Date();

		// 将用户手机号加入黑名单库中
		Map<String,Object> paramMap = new HashMap();
		paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
		paramMap.put("dimensionvalue",userBaseInfo.getPhone());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_DELETE);
		paramMap.put("lastmodifytime",date);
		nameBlacklistMapper.updateNameBlacklistStatus(paramMap);

		paramMap.clear();
        paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_PHONE);
        paramMap.put("dimensionvalue",userBaseInfo.getPhone());
		paramMap.put("source", BlacklistConstant.SOURCE_ADD);
		NameBlacklist nameBlack = nameBlacklistMapper.findSelective(paramMap);
		if (nameBlack == null){
			nameBlack = new NameBlacklist();
			nameBlack.setCreatetime(new Date());
			nameBlack.setDimensionkey(BlacklistConstant.DIMENSION_KEY_PHONE);
			nameBlack.setDimensionvalue(userBaseInfo.getPhone());
			nameBlack.setLastmodifytime(new Date());
			nameBlack.setSource(BlacklistConstant.SOURCE_ADD);
			nameBlack.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			int countNameBlackPhone = nameBlacklistMapper.save(nameBlack);
			if (countNameBlackPhone != 1){
				throw new BussinessException("用户添加手机号黑名单失败 nameBlack ==>"+nameBlack);
			}
		}

        // 将用户身份证加入黑名单库中
        paramMap.clear();
        paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
        paramMap.put("dimensionvalue",userBaseInfo.getIdNo());
		paramMap.put("status",BlacklistConstant.BLACK_LIST_STATUS_DELETE);
		paramMap.put("lastmodifytime",date);
		nameBlacklistMapper.updateNameBlacklistStatus(paramMap);

		paramMap.clear();
        paramMap.put("dimensionkey", BlacklistConstant.DIMENSION_KEY_IDNO);
        paramMap.put("dimensionvalue",userBaseInfo.getIdNo());
        paramMap.put("source", BlacklistConstant.SOURCE_ADD);
        nameBlack = nameBlacklistMapper.findSelective(paramMap);
        if (nameBlack == null){
			nameBlack = new NameBlacklist();
			nameBlack.setCreatetime(new Date());
			nameBlack.setDimensionkey(BlacklistConstant.DIMENSION_KEY_IDNO);
			nameBlack.setDimensionvalue(userBaseInfo.getIdNo());
			nameBlack.setLastmodifytime(new Date());
			nameBlack.setSource(BlacklistConstant.SOURCE_ADD);
			nameBlack.setStatus(BlacklistConstant.BLACK_LIST_STATUS_NORMAL);
			int countNameBlackIdNo = nameBlacklistMapper.save(nameBlack);
			if (countNameBlackIdNo != 1){
				throw new BussinessException("用户添加身份证黑名单失败 nameBlack ==>"+nameBlack);
			}
        }
	}

	private String findBorrowDay(long userId) {
		String remark = null;
		Map<String, Object> searchMap = new HashMap<>();
		searchMap.put("userId", userId);
		List<RepayModel> modelList = clBorrowMapper.findRepay(searchMap);
		for (RepayModel repayModel : modelList) {
			if (repayModel != null) {
				int day = DateUtil.daysBetween(DateUtil.getNow(), repayModel.getRepayTime());
				if (day > 0) {
					remark = "您需要" + day + "天后还款" + repayModel.getAmount() + "元";
				} else if (day == 0) {
					remark = "您需要在今天还款" + repayModel.getAmount() + "元";
				}
			}
		}
		return remark;
	}

	/**
	 * 借款详细信息
	 */
	@SuppressWarnings("static-access")
	@Override
	public ManageBorrowModel getModelByBorrowId(long borrowId) {
		ManageBorrowModel model = new ManageBorrowModel();
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow == null) {
			logger.error("查询的借款标不存在");
		} else {
			model = model.instance(borrow);
			// model.setBorrowId(borrow.getId());
			UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
			if (userBaseInfo != null) {
				model.setPhone(userBaseInfo.getPhone());
				model.setRealName(userBaseInfo.getRealName());
			}

			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			paramMap.put("state", BorrowProgressModel.PROGRESS_LOAN_SUCCESS);
			BorrowProgress bp = borrowProgressMapper.findFirst(paramMap);
			if (bp != null) {
				model.setLoanTime(bp.getCreateTime());
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			BorrowRepay borrowRepay = borrowRepayMapper.findByBorrowIdState(paramMap);
			if (borrowRepay != null) {
				model.setPenaltyAmout(borrowRepay.getPenaltyAmout());
				model.setPenaltyDay(borrowRepay.getPenaltyDay());
				if(borrowRepay.getAmount() != null){
					model.setRepayTotal(BigDecimalUtil.add(borrowRepay.getAmount(),borrowRepay.getPenaltyAmout()));
				} else {
					model.setRepayTotal(0.0);
				}
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			paramMap.put("type", BorrowRepayLogModel.REPAY_TYPE_CHARGE);
			BorrowRepayLog borrowRepaylog = borrowRepayLogMapper
					.findSelective(paramMap);
			if (borrowRepaylog != null) {
				model.setRepayTime(DateUtil.dateStr(borrowRepaylog.getRepayTime(),DateUtil.DATEFORMAT_STR_001));
				model.setRepayAmount(borrowRepaylog.getAmount());
				if(borrowRepay.getAmount() != null){
					model.setRepayYesTotal(BigDecimalUtil.add(borrowRepaylog.getAmount(),borrowRepaylog.getPenaltyAmout()));
				} else {
					model.setRepayYesTotal(0.0);
				}
			 }
			
			paramMap = new HashMap<String, Object>();
			paramMap.put("borrowId", borrowId);
			UrgeRepayOrder order=urgeRepayOrderMapper.findSelective(paramMap);
			if(order!=null){
				model.setLevel(order.getLevel());
			 }
			}
		
		return model;
	}

	@Override
	public Page<ManageBorrowModel> listBorrowModel(Map<String, Object> params, int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = clBorrowMapper.listBorrowModel(params);
		return (Page<ManageBorrowModel>) list;
	}

	public List<ManageBorrowModel> listBorrowModel(Map<String, Object> params) {
		List<ManageBorrowModel> list = clBorrowMapper.listBorrowModel(params);
		return  list;
	}
	
	@Override
	public void updatePayState(Map<String, Object> paramMap){
		int result  =  clBorrowMapper.updatePayState(paramMap);
		if(result < 1){
			throw new BussinessException("当前借款状态不允许修改");
		}
	}

	@Override
	public Borrow findByPrimary(Long borrowId) {
		return clBorrowMapper.findByPrimary(borrowId);
	}
	
	@Override
	public ClBorrowModel rcBorrowApply(final Borrow borrow, String tradePwd, String mobileType, boolean xwldFlag) throws Exception {
		ClBorrowModel clBorrow = new ClBorrowModel();
		Borrow realBorrow = null;
		// 处理用户通话详情统计
		fixedThreadPool.execute(new Runnable() {
			public void run(){
				Map<String, Object> queryMap = new HashMap<>();
				queryMap.put("userId", borrow.getUserId());
				OperatorReqLog operatorReqLog = operatorReqLogMapper.findLastRecord(queryMap);
				String tableName1 = ShardTableUtil.generateTableNameById("cl_operator_voice_cnt", borrow.getUserId(), 30000);
				int count = operatorVoiceCntMapper.countNotNull(tableName1, borrow.getUserId(), operatorReqLog.getId());
				if(count == 0) {
					String tableName2 = ShardTableUtil.generateTableNameById("cl_operator_voice", borrow.getUserId(), 30000);
					Map<String, Date> lastContactMap = new HashMap<>();
					List<Map<String, String>> lastContactTimes = operatorVoiceMapper.getLastContactTime(tableName2, borrow.getUserId(), operatorReqLog.getId());
					if(lastContactTimes != null) {
						for (Map<String, String> lastContactTime : lastContactTimes) {
							lastContactMap.put(lastContactTime.get("peer_number"), DateUtil.parse(lastContactTime.get("last_contact_time"), "yyyy-MM-dd HH:mm:ss"));
						}
					}

					if(lastContactMap.size() > 0) {
						for (String key : lastContactMap.keySet()) {
							Map<String, Object> updateMap = new HashMap<>();
							updateMap.put("tableName", tableName1);
							updateMap.put("userId", borrow.getUserId());
							updateMap.put("peerNumber", key);
							updateMap.put("reqLogId", operatorReqLog.getId());
							updateMap.put("lastContactTime", lastContactMap.get(key));
							operatorVoiceCntMapper.updateLastContactTime(updateMap);
						}
					}
				}
			}
		});
		// 校验用户是否符合借款条件
		boolean isCanBorrow = isCanBorrow(borrow,tradePwd);
		if(isCanBorrow){
			realBorrow = saveBorrow(borrow);
			BeanUtils.copyProperties(realBorrow, clBorrow);
			clBorrow.setNeedApprove(true);
		}
		
		if (realBorrow != null && realBorrow.getId() > 0) {
			long borrowId = realBorrow.getId();
			savePressState(realBorrow, BorrowModel.STATE_PRE,"");
			modifyCredit(realBorrow.getUserId(),realBorrow.getAmount(),"used");
			
			UserBaseInfo userBaseInfo = userBaseInfoMapper.findByUserId(borrow.getUserId());
			//如果用户在白名单中，直接放款
			if(UserBaseInfoModel.USER_STATE_WHITE.equals(userBaseInfo.getState())){
				handleBorrow(BorrowRuleResult.RESULT_TYPE_PASS, borrow,"");
				clBorrow.setNeedApprove(false);
				return clBorrow;
				
			//如果用户在黑名单，直接拒绝
			} else if(UserBaseInfoModel.USER_STATE_BLACK.equals(userBaseInfo.getState())){
				handleBorrow(BorrowRuleResult.RESULT_TYPE_REFUSED, borrow,"很遗憾，您未通过审核，原因：黑名单用户");
				clBorrow.setNeedApprove(false);
				return clBorrow;
			}

			List<TppServiceInfoModel> infoList = sceneBusinessMapper.findTppServiceInfo();
			//不需要执行有可用历史记录的数量
			logger.debug("审核需要执行的接口信息"+JSONObject.toJSONString(infoList));
			//如果配置了风控数据,先调用风控数据接口
			if (infoList != null && infoList.size() > 0) {
				SceneBusinessLog sceneLog = null;
				for(TppServiceInfoModel info : infoList){
					//忽略新颜行为雷达查询
					if(!xwldFlag && TppBusinessModel.BUS_NID_XWLD.equals(info.getBusNid())) {
						continue;
					}
					if(OcrConstant.OCR_TYPE_FACE.equals(userBaseInfo.getIdType()) && TppBusinessModel.BUS_NID_YOUDUN.equals(info.getBusNid())) {
						continue;
					}
					boolean needExcute = sceneBusinessLogService.needExcute(realBorrow.getUserId(),info.getBusId(),info.getGetWay(),info.getPeriod());
					if(needExcute){
						sceneLog = new SceneBusinessLog(info.getSceneId(), realBorrow.getId(), realBorrow.getUserId(), info.getTppId(), info.getBusId(), info.getBusNid(), realBorrow.getCreateTime(),info.getType());
						sceneBusinessLogMapper.save(sceneLog);
					}
				}
			}
		} else {
			throw new BussinessException("借款失败");
		}
		return clBorrow;
	}
	
	public void getThirdServiceData(final Borrow borrow,final String nid,final Long tppId,final String mobileType){
		//魔杖-反欺诈报告
		if("MagicAntiFraud".equals(nid)){
			logger.info("进入魔杖反欺诈报告查询");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = magicRiskService.queryAntiFraud(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
		//魔杖-黑灰名单
		} else if ("MagicBlackGray".equals(nid)) {
			logger.info("进入魔杖黑灰名单查询");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = magicRiskService.queryBlackGray(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			//新颜小额网贷报告
		} else if (TppBusinessModel.BUS_NID_XWLD.equals(nid)) {
			logger.info("进入新颜行为雷达报告查询,等待新颜回调结果");
			//宜信风险评估报告
		} else if ("YixinRisk".equals(nid)) {
			logger.info("进入宜信风险评估查询");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = yixinRiskService.queryRisk(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			//宜信欺诈甄别
		} else if ("YixinFraud".equals(nid)) {
			logger.info("进入宜信欺诈甄别查询");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = yixinRiskService.queryFraud(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			//宜信欺诈甄别
		} else if ("Operator".equals(nid)) {
			logger.info("进入运营商数据处理");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = borrowOperatorLogService.saveLog(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			//凭安染黑度统计
		} else if ("PinganGrayscaleStat".equals(nid)) {
			logger.info("进入凭安染黑度统计查询");
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					int count = pinganRiskService.queryGrayscaleStat(borrow);
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});
			//有盾数据查询
		} else if (TppBusinessModel.BUS_NID_YOUDUN.equals(nid)){
			logger.info("进入有盾数据处理");
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
                    int count = youDunRiskService.multiPoint(borrow);
                    syncSceneBusinessLog(borrow.getId(), nid, count);
                }
			});

		}
		else {
			logger.error("没有找到"+nid+"对应的第三方接口信息");
		}
	}
	
	@Override
	public void rcBorrowRuleVerify(Long borrowId){
		Borrow borrow = getById(borrowId);
		//计算借款订单对应决策数据的值
		decisionService.saveBorrowDecision(borrow);
		Map<String, Object> modelData = clBorrowMapper.getModelData(borrowId);

		//如果是复借用户,直接机审通过
		int finishCount = clBorrowMapper.finishCount(borrow.getUserId()); // 借款完成次数
		if (finishCount > 0) {
			logger.info("用户userId" + borrow.getUserId() + "为复借用户,直接机审通过");
			handleBorrow(BorrowRuleResult.RESULT_TYPE_REVIEW, borrow, "复借用户机审直接通过,待人工复审");
			return;
		}

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("state", 10);
		List<RuleEngine> ruleEngieList = ruleEngineMapper.listSelective(paramMap);
		//没有找到规则配置，则借款不进行任何处理
		if(ruleEngieList == null || ruleEngieList.isEmpty()) {
			return;
		}
		
		boolean review = false;
		paramMap.clear();
		paramMap.put("adaptedId", "10");
		List<RuleEngineConfig> configCollection = ruleEngineConfigMapper.findRuleEnginConfigForBorrow(paramMap);
		BorrowRuleResult result;
		for (int i = 0; i < configCollection.size(); i++) {
			RuleEngineConfig config = configCollection.get(i);
			Long engineId =config.getRuleEnginId();
			result = new BorrowRuleResult(borrowId,config.getRuleEnginId(),config.getCtable(),config.getTableComment(),config.getCcolumn(),config.getColumnComment(),config.getFormula(),new Date());
			String tableName = config.getCtable();
			
			//使用分表的数据表，需要获取当前使用的表名称
			tableName = ShardTableUtil.generateTableNameById(config.getCtable(), borrow.getUserId(), 30000);
			
			//取数据库字段的值
			String statement = "select " + config.getCcolumn() + " from " + tableName + " where user_id = " + borrow.getUserId()+" order by id desc limit 1";
			String value = ruleEngineMapper.findValidValue(statement);
			result.setValue(config.getCvalue());
			
			//进行值的比对，返回是否匹配,数据库没有值，进入人工复审
			boolean hasValue = StringUtil.isNotBlank(value);
			String coparResult = hasValue?comparRule(config,value):SimpleRule.COMPAR_FAIL;
			result.setResult(coparResult);
			result.setMatching(hasValue?value:"未知");
			if(!hasValue){
				review = true;
			}
			
			String type = config.getEnginType();
			
			//如果是结果模式，则设置结果类型
			if(RuleEngineConfig.ENGINE_RESULT.equals(type)){
				String resultType = hasValue?config.getResult():BorrowRuleResult.RESULT_TYPE_REVIEW;
				result.setResultType(resultType);
				//保存比对结果
				borrowRuleResultMapper.save(result);
				//命中审核不通过,则规则停止执行并处理借款审核结果
				if(SimpleRule.COMPAR_PASS.equals(coparResult) && BorrowRuleResult.RESULT_TYPE_REFUSED.equals(result.getResultType())){
					//处理借款申请，规则停止执行
					handleBorrow(result.getResultType(), borrow,"");
					return;
				//命中人工复审
				}else if(SimpleRule.COMPAR_PASS.equals(coparResult) && BorrowRuleResult.RESULT_TYPE_REVIEW.equals(result.getResultType())){
					//人工复审
					review = true;
				}
			//如果是评分模式，设置分数
			}else if(RuleEngineConfig.ENGINE_SCORE.equals(type)){
				Integer score = 0;
				if(SimpleRule.COMPAR_PASS.equals(result.getResult())){
					score = hasValue?config.getIntegral():0;
				}
				result.setScore(score);
				//保存比对结果
				borrowRuleResultMapper.save(result);
			}else{
				throw new BussinessException("规则模式设置错误");
			}
			
			//(评分模式) 本条规则执行完成，之后统计总分
			if(i== (configCollection.size() - 1) && RuleEngineConfig.ENGINE_SCORE.equals(type)){
				
				//统计规则总得分
				Integer score = borrowRuleResultMapper.sumScoreByRuleId(engineId,borrow.getId());
				paramMap.clear();
				paramMap.put("ruleEnginId", engineId);
				//查询决策引擎列表
				List<RuleEngineInfo> engineInfoList =  ruleEngineInfoMapper.listSelective(paramMap);
				//根据规则Id开始对评分进行汇总，决策引擎生效
				for(int k=0;k<engineInfoList.size();k++){
					RuleEngineInfo info = engineInfoList.get(k);
					String scoreStr = score + "";
					SimpleRule simpleRule = RulesExecutorUtil.singleRuleResult(info.getId(),"score",info.getFormula(),info.getIntegral(), scoreStr,"int", "");
					//保存决策引擎执行结果
					BorrowScoreResult scoreResult = new BorrowScoreResult(borrow.getId(),engineId,info.getResult(),info.getFormula(),info.getIntegral(),score,simpleRule.getComparResult());
					borrowScoreResultMapper.save(scoreResult);
					//判断借款是否需要处理
					if(SimpleRule.COMPAR_PASS.equals(simpleRule.getComparResult()) && BorrowRuleResult.RESULT_TYPE_REFUSED.equals(info.getResult())) {
						//处理借款申请，规则停止执行
						handleBorrow(info.getResult(), borrow,"");
						return;
					}else if(SimpleRule.COMPAR_PASS.equals(simpleRule.getComparResult()) &&  BorrowRuleResult.RESULT_TYPE_REVIEW.equals(info.getResult()) ){
						review = true;
					}
				}
			}
				
			
			
			// 直到规则执行到最后一项，如果没有命中人工复审或者审核不通过  ，则借款申请为审核通过
			if (i == (configCollection.size() - 1)) {
				//对借款申请进行审核处理
				if(review){
					handleBorrow(BorrowRuleResult.RESULT_TYPE_REVIEW, borrow,"");
				}else{
					handleBorrow(BorrowRuleResult.RESULT_TYPE_PASS, borrow,"");
				}
				
			}
		}	
	}
	/**
	 * 获得规则比对结果
	 * @param config
	 * @param value
	 * @return
	 */
	public String comparRule(RuleEngineConfig config,String value){
		SimpleRule simpleRule = RulesExecutorUtil.singleRuleResult(config.getId(),config.getCcolumn(),config.getFormula(),config.getCvalue(), value,config.getType(), "");
		return simpleRule.getComparResult();
	}
	
	/**
	 * 规则命中审核不通过或者人工复审时，对借款的处理
	 * @param resultType
	 * @param borrow
	 * @param remark 备注
	 */
	public void handleBorrow(String resultType,Borrow borrow,String remark){
		UserBaseInfo userInfo = userBaseInfoService.findByUserId(borrow.getUserId());
		if (BorrowRuleResult.RESULT_TYPE_REFUSED.equals(resultType)) {
			int result = modifyState(borrow.getId(), BorrowModel.STATE_AUTO_REFUSED,BorrowModel.STATE_PRE);
			logger.info("自动审核不通过result: "+result);
			if(result == 1){
				savePressState(borrow, BorrowModel.STATE_AUTO_REFUSED,remark);
				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
			}
			//clSmsService.borrowResult(borrow.getCreateTime(), SmsModel.SMS_TYPE_BORROWREFUSED, userInfo.getPhone());
		} else if (BorrowRuleResult.RESULT_TYPE_REVIEW.equals(resultType)) {
			int result = modifyState(borrow.getId(), BorrowModel.STATE_NEED_REVIEW,BorrowModel.STATE_PRE);
			logger.info("自动审核未决待人工复审result: "+result);
			if(result == 1){
				//插入人工审核订单表
				manualReviewOrderMapper.save(getManualReviewOrder(borrow.getId(), userInfo));
				savePressState(borrow, BorrowModel.STATE_NEED_REVIEW,remark);
			}
		} else if(BorrowRuleResult.RESULT_TYPE_PASS.equals(resultType)){
			if(!"20".equals(Global.getValue("review_loan"))) {
				int result = modifyState(borrow.getId(), BorrowModel.STATE_NEED_REVIEW,BorrowModel.STATE_PRE);
				logger.info("自动审核通过,人工复审开关打开,待人工复审result: "+result);
				if(result == 1) {
					//插入人工审核订单表
					manualReviewOrderMapper.save(getManualReviewOrder(borrow.getId(), userInfo));
					//状态修改为待人工复审
					savePressState(borrow, BorrowModel.STATE_NEED_REVIEW,remark);
				}
			} else {
				int result = modifyState(borrow.getId(), BorrowModel.STATE_AUTO_PASS,BorrowModel.STATE_PRE);
				if(result == 1) {
					logger.info("自动审核通过result: "+result);
					savePressState(borrow, BorrowModel.STATE_AUTO_PASS,remark);
					if (!"10".equals(Global.getValue("manual_loan"))) { // 系统配置的是否放款审核
						// 放款
						borrowLoan(borrow, new Date());
					} else {
						// 待放款审核
						result = modifyState(borrow.getId(), BorrowModel.WAIT_AUDIT_LOAN,BorrowModel.STATE_AUTO_PASS);
						logger.info("自动审核通过 待放款审核result: "+result);
						if(result == 1){
							savePressState(borrow, BorrowModel.WAIT_AUDIT_LOAN,"");
						}
					}
				}
			}
		}
	}
 
	
	public void getStatisticsServiceData(final Borrow borrow,final String nid){
		if("simple_borrow_count".equals(nid)){ //（简）借款统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleBorrowCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else if("simple_contact_count".equals(nid)){ //（简）通讯录统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleContactCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else if("simple_voices_count".equals(nid)){ //（简）通话记录统计
			Thread t = new Thread(new Runnable() {  
				public void run(){
					int count = simpleVoicesCountService.countOne(borrow.getUserId());
					syncSceneBusinessLog(borrow.getId(), nid, count);
				}
			});  
			t.start();
		} else{
			logger.error("没有找到"+nid+"对应的统计处理");
		}
	}
	
	@Override
	public void syncSceneBusinessLog(final Long borrowId, String nid, int count) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		params.put("nid", nid);
		SceneBusinessLog log = sceneBusinessLogMapper.findSelective(params);
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if(log != null){
			String state = "0";
			String desc = "失败";
			if (count > 0) {
				state = "1";
				desc = "成功";
			}
			log.setUpdateTime(new Date());
			log.setRsState(state);
			log.setRsDesc(desc);
			int result = sceneBusinessLogMapper.update(log);
			logger.info("syncSceneBusinessLog，borrowId："+borrowId+"，nid："+nid+"，syncSceneBusinessLog更新结果："+result);
			boolean haveNeed = sceneBusinessLogService.haveNeedExcuteService(borrowId);
			if(!haveNeed){
				logger.info("统计接口接口审核结果——借款borrow" + borrow.toString());
				borrow.setSubState("11");
				int i = clBorrowMapper.updatesub(borrow);
				logger.info("统计接口接口审核结果——i的数据" + i);
				if (i > 0) {
					rcBorrowRuleVerify(borrowId);
				}
			}
		} else {
			logger.error("syncSceneBusinessLog，borrowId："+borrowId+"，nid："+nid+"，未找到对应的sceneBusinessLog");
		}
	}
	
	@Override
	public void syncSceneBusinessLog(String state, String desc, final Long borrowId, String nid) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("borrowId", borrowId);
		params.put("nid", nid);
		SceneBusinessLog log = sceneBusinessLogMapper.findSelective(params);
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if(log != null){
			log.setUpdateTime(new Date());
			log.setRsState(state);
			log.setRsDesc(desc);
			sceneBusinessLogMapper.update(log);
			boolean haveNeed = sceneBusinessLogService.haveNeedExcuteService(borrowId);
			if(!haveNeed){
				logger.info("接口异步通知——借款borrow" + borrow.toString());
				borrow.setSubState("11");
				int i = clBorrowMapper.updatesub(borrow);
				logger.info("接口异步通知——i的数据" + i);
				if (i > 0) {
					rcBorrowRuleVerify(borrowId);
				}
			}
		}
	}

	/**
	 * 执行异步任务
	 */
	private void submitTask(Borrow borrow) {
		//调用德信数聚灰名单
		Map<String, BlacklistProcess> taskMap = BlacklistUtil.getBaseTaskHashMap();
		for (Map.Entry<String, BlacklistProcess> entry : taskMap.entrySet()) {
			BlacklistProcess task = entry.getValue();
			if (task != null) {
				fixedThreadPool.submit(new BlacklistBaseTask(task,borrow));
			}
		}
		fixedThreadPool.submit(new XindeDataTask(borrow));
	}

	@Override
	public void verifyBorrowData(long borrowId, String mobileType) {
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		submitTask(borrow);//提交异步任务
		List<SceneBusinessLog> sceneLogList = sceneBusinessLogMapper.findSceneLogByBorrowId(borrowId);
		
		if(borrow != null){
			if (sceneLogList != null && !sceneLogList.isEmpty()){
				for(SceneBusinessLog log : sceneLogList){
					if(TppServiceInfoModel.SERVICE_TYPE_STATISTICS.equals(log.getType())){
						getStatisticsServiceData(borrow,log.getNid());
					} else if(TppServiceInfoModel.SERVICE_TYPE_THIRD.equals(log.getType())){
						getThirdServiceData(borrow, log.getNid(), log.getTppId(), mobileType);
					}
				}
			} else {
				rcBorrowRuleVerify(borrowId);
			}
		}
	}

	@Override
	public void reVerifyBorrowData(Long borrowId) {
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		List<SceneBusinessLog> sceneLogList = sceneBusinessLogMapper.findSceneLogByBorrowId(borrowId);
		if(borrow != null && borrow.getState().equals(BorrowModel.STATE_PRE)){
			UserBaseInfo userInfo = userBaseInfoMapper.findByPrimary(borrow.getUserId());
			if (sceneLogList != null && !sceneLogList.isEmpty()){
				logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入征信数据重获取流程");
				
				for(SceneBusinessLog log : sceneLogList){
					//sceneBusinessLogMapper.save(log);
					if(TppServiceInfoModel.SERVICE_TYPE_STATISTICS.equals(log.getType())){
						getStatisticsServiceData(borrow,log.getNid());
						
					} else if(TppServiceInfoModel.SERVICE_TYPE_THIRD.equals(log.getType())){
						getThirdServiceData(borrow,log.getNid(),log.getTppId(),"0");
					}
				}
			} else if (sceneLogList == null || sceneLogList.isEmpty()) {
				Map<String, Object> ruleResultMap = new HashMap<String, Object>();
				ruleResultMap.put("borrowId", borrowId);
				List<BorrowRuleResult> resultList = borrowRuleResultMapper.listSelective(ruleResultMap);
				if (resultList == null || resultList.isEmpty()) {
					logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入规则审核流程");
					rcBorrowRuleVerify(borrowId);
				} else if (BorrowModel.STATE_AUTO_PASS.equals(borrow.getState())) {
					if(!"20".equals(Global.getValue("review_loan"))) {
						modifyState(borrow.getId(), BorrowModel.STATE_NEED_REVIEW,BorrowModel.STATE_AUTO_PASS);
						//插入人工审核订单表
						manualReviewOrderMapper.save(getManualReviewOrder(borrow.getId(), userInfo));
						//状态修改为待人工复审
						logger.info("自动审核通过,人工复审开关打开,待人工复审");
						savePressState(borrow, BorrowModel.STATE_NEED_REVIEW,StringUtil.EMPTY);
					} else {
						if (!"10".equals(Global.getValue("manual_loan")))  { //系统配置的是否放款审核
							logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入放款流程");
							borrowLoan(borrow, DateUtil.getNow());
						} else {
							//待放款审核
							int result = modifyState(borrow.getId(), BorrowModel.WAIT_AUDIT_LOAN,borrow.getState());
							logger.info("待放款审核result: "+result);
							if(result == 1){
								savePressState(borrow, BorrowModel.WAIT_AUDIT_LOAN,"");
							}
						}
					}
				} else if (BorrowModel.STATE_PASS.equals(borrow.getState())) {
					if (!"10".equals(Global.getValue("manual_loan")))  { //系统配置的是否放款审核
						logger.info("风控审核重触发，borrowId："+borrow.getId()+"进入放款流程");
						borrowLoan(borrow, DateUtil.getNow());
					} else {
						//待放款审核
						int result = modifyState(borrow.getId(), BorrowModel.WAIT_AUDIT_LOAN,borrow.getState());
						logger.info("待放款审核result: "+result);
						if(result == 1){
							savePressState(borrow, BorrowModel.WAIT_AUDIT_LOAN,"");
						}
					}
				}
			} else {
				logger.info("风控审核重触发，borrowId："+borrow.getId()+"，不满足执行条件，执行失败");
			}
		}
	}
	
	public List listBorrow(Map<String, Object> params) {
		List<ManageBorrowModel> list = clBorrowMapper.listBorrowModel(params);
		for (ManageBorrowModel model : list) {
			model.setState(BorrowModel.apiConvertBorrowState(model.getState()));
			UserBaseInfo ubi = userBaseInfoMapper.findByUserId(model.getUserId());
			if (ubi != null) {
				model.setRealName(ubi.getRealName());
				model.setPhone(ubi.getPhone());
			}
			Map<String, Object> params2 = new HashMap<>();
			params2.put("borrowId", model.getId());
			params2.put("state", BorrowModel.STATE_REPAY);
			BorrowProgress bp = borrowProgressMapper.findFirst(params2);
			if (bp != null) {
				model.setLoanTime(bp.getCreateTime());
			}
			Map<String, Object> params3 = new HashMap<>();
			params3.put("borrowId", model.getId());
			BorrowRepay br = borrowRepayMapper.findByBorrowIdState(params3);
			if (br != null) {
				model.setPenaltyDay(br.getPenaltyDay());
				model.setPenaltyAmout(br.getPenaltyAmout());
			}
			UrgeRepayOrder uro = urgeRepayOrderMapper.findSelective(params3);
			if (uro != null) {
				model.setLevel(uro.getLevel());
			}
			params3.put("type", BorrowRepayLogModel.REPAY_TYPE_CHARGE);
			BorrowRepayLog brl = borrowRepayLogMapper.findSelective(params3);
			if (brl != null) {
				model.setRepayAmount(brl.getAmount());
				model.setRepayTime(DateUtil.dateStr2(brl.getRepayTime()));
			}

		}
		return list;
	}

	@Override
	public Page<ManageBorrowModel> listReview(Map<String,Object> params,int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<ManageBorrowModel> list = clBorrowMapper.listReview(params);
		return (Page<ManageBorrowModel>) list;
	}

	@Override
	public List<Borrow> findUserUnFinishedBorrow(long userId) {
		return clBorrowMapper.findUserUnFinishedBorrow(userId);
	}

	@Override
	public Borrow findLastBorrow(long userId) {
		return clBorrowMapper.findLastBorrow(userId);
	}
	
	@Override
	public int modifyBorrowState(long borrowId, long userId, String state) {
		// 更新借款状态
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", borrowId);
		params.put("state", state);
		int count = clBorrowMapper.syncUpdateState(params);
		
		if (count > 0) { // 借款状态更新成功、添加借款进度
			BorrowProgress bp = new BorrowProgress();//userId, borrowId, state, BorrowModel.convertBorrowRemark(state)
			bp.setUserId(userId);
			bp.setBorrowId(borrowId);
			bp.setState(state);
			bp.setBorrowId(borrowId);
			bp.setRemark("还款处理中,请稍后");
			bp.setCreateTime(DateUtil.getNow());
			count = borrowProgressMapper.save(bp);
		}
		return count;
	}
	
	//改变总额度
	@Override
	public void changeCreditTotal(double total) {
		Map<String, Object> searchMap = new HashMap<String, Object>();
        double change = total - Global.getDouble("init_credit");
        if (change == 0)
            return;
        Map<String,Double> map = new HashMap<String, Double>();
        map.put("total", total);
        map.put("change", Math.abs(change));
        if (change<0) {
        	creditMapper.reduceUpdate(map);
		}else {
			creditMapper.addUpdate(map);
		}
        creditMapper.balance();

	}
	
	/**
	 * 审核放款
	 */
	@Override
	public int auditBorrowLoan(Long borrowId, String state, String remark,Long userId) {
		int code = 0;
		Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow != null) {
			if(!borrow.getState().equals(BorrowModel.WAIT_AUDIT_LOAN)){
				logger.error("审核失败,当前状态不是待审核放款");
				throw new BussinessException("审核失败,当前状态不是待审核放款");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("id", borrowId);
			map.put("state", state);
			map.put("remark", remark);
			code = clBorrowMapper.loanState(map);
			if (code!=1) {
				throw new BussinessException("放款审核失败,当前状态不是待审核放款");
			}
			
			// savePressState(borrow, state);
			//添加借款进度
			BorrowProgress borrowProgress = new BorrowProgress();
			borrowProgress.setBorrowId(borrow.getId());
			borrowProgress.setUserId(borrow.getUserId());
			borrowProgress.setState(state);
			borrowProgress.setRemark("人工放款审核");
			
			borrowProgress.setAuditRemark(remark);
			borrowProgress.setAuditUser(userId);
			borrowProgress.setCreateTime(DateUtil.getNow());
			borrowProgressMapper.save(borrowProgress);
			
			if (BorrowModel.AUDIT_LOAN_FAIL.equals(state)) {
				// 审核不通过返回信用额度
				modifyCredit(borrow.getUserId(), borrow.getAmount(), "unuse");
			}
			// 审核放款通过 放款
			if (BorrowModel.AUDIT_LOAN_PASS.equals(state)) {
				borrowLoan(borrow, new Date());
			}
		} else {
			logger.error("审核放款失败，当前标不存在");
			throw new BussinessException("审核放款失败，当前标不存在");
		}
		return code;
	}

	@Override
	public List<YixinShareModel> queryDataForYixin(Long userId, String idNo, String name) {
		List<YixinShareModel> yixinShareModels = clBorrowMapper.queryDataForYixin(userId);
		for (YixinShareModel model : yixinShareModels) {
			model.setIdNo(idNo);
			model.setName(name);
			model.setOverdueM3(null);
			model.setOverdueM6(null);
			if("OVERDUE".equals(model.getLoanStatus())) {
				if(StringUtil.isNotBlank(model.getOverdueStatus())) {
					int i = (Integer.valueOf(model.getOverdueStatus()) + 29) / 30;
					if(i > 6) {
						model.setOverdueM3(1);
						model.setOverdueM6(1);
					} if(i > 3) {
						model.setOverdueM3(1);
					}
					if(i > 6) {
						model.setOverdueStatus("M6+");
					} else {
						model.setOverdueStatus("M" + i);
					}
				}
			}
		}
		return yixinShareModels;
	}

	@Override
	public int comeBackBorrow(long borrowId) {
        Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
        //查询该用户的最后一条借款的id
        Long lastId = clBorrowMapper.findLastBorrow(borrow.getUserId()).getId();

        if (borrowId!=lastId){
            return 2;
        }

        UserBaseInfo userInfo = userBaseInfoService.findByUserId(borrow.getUserId());

        if (userInfo==null){
            logger.info("该订单没有对应的用户信息.");
            return 0;
        }
        int result=0;
        if ("21".equals(borrow.getState())){
            //修改状态
             result = modifyState(borrowId, BorrowModel.STATE_NEED_REVIEW,BorrowModel.STATE_AUTO_REFUSED);
            //插入人工审核订单表
            manualReviewOrderMapper.save(getManualReviewOrder(borrowId, userInfo));

        } else if ("27".equals(borrow.getState())){

            result = modifyState(borrowId, BorrowModel.STATE_NEED_REVIEW,BorrowModel.STATE_REFUSED);
            HashMap<String, Object> map = new HashMap<>();
            //更新人工审核订单表
            map.put("borrowId", borrowId);
            map.put("reviewTime", DateUtil.getNow());
            map.put("state", UrgeRepayOrderModel.STATE_ORDER_PRE);
            manualReviewOrderMapper.updateByBorrowId(map);
        }
		logger.info("审核不通过(自动审核不通过,人工复审不通过),拉回重审,返回结果result: "+result);
		if(result == 1){
			//信用额度修改
            modifyCredit(borrow.getUserId(), borrow.getAmount(), "used");
            //添加借款进度
			savePressState(borrow, BorrowModel.STATE_NEED_REVIEW,"");

			return 1;
		}

		return 0;
	}

	private Map<String,Object> getFeeMap(double fee){
		Map<String,Object> feeMap=new HashMap<>();
		String fee_map = Global.getValue("fee_map");
		String[] feesList = fee_map.split(",");
		List<String> lists=Arrays.asList((feesList[0]).split("-"));
		feeMap.put("serviceFeeName", lists.get(0));
		feeMap.put("serviceFee", lists.get(1));
		lists=Arrays.asList((feesList[1]).split("-"));
		feeMap.put("infoAuthFeeName", lists.get(0));
		feeMap.put("infoAuthFee", lists.get(1));
		lists=Arrays.asList((feesList[2]).split("-"));
		feeMap.put("interestName", lists.get(0));
		feeMap.put("interest", lists.get(1));
		double serviceFee = Double.valueOf((String) feeMap.get("serviceFee"));// 服务费
		double infoAuthFee = Double.valueOf((String) feeMap.get("infoAuthFee"));// 信息认证费
		double interest = Double.valueOf((String) feeMap.get("interest"));// 利息
		serviceFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, serviceFee));
		infoAuthFee = BigDecimalUtil.round(BigDecimalUtil.mul(fee, infoAuthFee));
		interest = BigDecimalUtil.sub(fee, serviceFee, infoAuthFee);
		Map<String,Object> returnMap=new HashMap<>(feeMap);
		returnMap.put("serviceFee", serviceFee);
		returnMap.put("infoAuthFee", infoAuthFee);
		returnMap.put("interest", interest);
		return returnMap;
	}

	private ManualReviewOrder getManualReviewOrder(Long borrowId, UserBaseInfo userInfo) {
		ManualReviewOrder manualReviewOrder = new ManualReviewOrder();
		manualReviewOrder.setCreateTime(DateUtil.getNow());
		manualReviewOrder.setBorrowId(borrowId);
		manualReviewOrder.setBorrowName(userInfo.getRealName());
		manualReviewOrder.setPhone(userInfo.getPhone());
		manualReviewOrder.setState(ManualReviewOrderModel.STATE_ORDER_PRE);
		return manualReviewOrder;
	}

	private String getCredit(String borrowCredit, double userCredit) {
		String[] split = borrowCredit.split(",");
		String result = StringUtils.EMPTY;
		for (int i = 0; i < split.length; i++) {
			double creditTemp = Double.parseDouble(split[i]);
			int creditInt = new Double(creditTemp).intValue();
			if (userCredit >= creditInt) {
				if (i != 0) {
					result += ",";
				}
				result += creditInt;
			}
		}
		return result;
	}

	private List<Map<String,Object>> getNoUserChoices(String feeName) {
		Map<String,Object> map = new HashMap<>();
		map.put("fee", CreditConstant.FEE);
		map.put("feeName", feeName);
		map.put("realAmount", CreditConstant.AMOUNT - CreditConstant.FEE);
		Map<String, Object> feeDetail= new HashMap<>();
		List<Map<String,Object>> feeDetailList = new ArrayList<>();
		Map<String,Object> feeMap=getFeeMap(CreditConstant.FEE);
		feeDetail.put("title", feeMap.get("serviceFeeName"));
		feeDetail.put("value", feeMap.get("serviceFee"));
		feeDetailList.add(feeDetail);
		feeDetail= new HashMap<>();
		feeDetail.put("title", feeMap.get("infoAuthFeeName"));
		feeDetail.put("value", feeMap.get("infoAuthFee"));
		feeDetailList.add(feeDetail);
		feeDetail= new HashMap<>();
		feeDetail.put("title", feeMap.get("interestName"));
		feeDetail.put("value", feeMap.get("interest"));
		feeDetailList.add(feeDetail);
		map.put("feeDetailList", feeDetailList);

		map.put("timeLimit", CreditConstant.MIN_DAY);
		map.put("amount", CreditConstant.AMOUNT);

		//IOS端返回数据
		map.put("feeDetail", feeMap);
		List<Map<String,Object>> list = new ArrayList<>();
		list.add(map);


		Map<String,Object> map2 = new HashMap<>();
		map2.put("fee", CreditConstant.FEE);
		map2.put("feeName", feeName);
		map2.put("realAmount", CreditConstant.AMOUNT - CreditConstant.FEE);
		Map<String, Object> feeDetail2 = new HashMap<>();
		List<Map<String,Object>> feeDetailList2 = new ArrayList<>();
		Map<String,Object> feeMap2 = getFeeMap(CreditConstant.FEE);
		feeDetail2.put("title", feeMap2.get("serviceFeeName"));
		feeDetail2.put("value", feeMap2.get("serviceFee"));
		feeDetailList2.add(feeDetail2);
		feeDetail2= new HashMap<>();
		feeDetail2.put("title", feeMap2.get("infoAuthFeeName"));
		feeDetail2.put("value", feeMap2.get("infoAuthFee"));
		feeDetailList2.add(feeDetail2);
		feeDetail2 = new HashMap<>();
		feeDetail2.put("title", feeMap2.get("interestName"));
		feeDetail2.put("value", feeMap2.get("interest"));
		feeDetailList2.add(feeDetail2);
		map2.put("feeDetailList", feeDetailList2);

		map2.put("timeLimit", CreditConstant.MAX_DAY);
		map2.put("amount", CreditConstant.AMOUNT);

		//IOS端返回数据
		map2.put("feeDetail", feeMap2);
		list.add(map2);
		return list;
	}

	@Override
	public int offlinePay(Long borrowId, Long userId) {
		int code = 0;
		final Borrow borrow = clBorrowMapper.findByPrimary(borrowId);
		if (borrow != null) {
			if(!borrow.getState().equals(BorrowModel.WAIT_AUDIT_LOAN) && !borrow.getState().equals(BorrowModel.STATE_REPAY_FAIL)){
				logger.error("线下放款失败,当前状态不是待审核放款或放款失败");
				throw new BussinessException("线下放款失败,当前状态不是待审核放款或放款失败");
			}
			Map<String,Object> map = new HashMap<>();
			map.put("id", borrowId);
			map.put("state", BorrowModel.STATE_REPAY);
			code = clBorrowMapper.loanState(map);
			if (code!=1) {
				throw new BussinessException("线下放款失败,当前状态不是待审核放款或放款失败");
			}

			//添加借款进度
			BorrowProgress borrowProgress = new BorrowProgress();
			borrowProgress.setBorrowId(borrow.getId());
			borrowProgress.setUserId(borrow.getUserId());
			borrowProgress.setState(BorrowModel.STATE_REPAY);
			borrowProgress.setRemark("线下放款审核");

			borrowProgress.setAuditUser(userId);
			borrowProgress.setCreateTime(DateUtil.getNow());
			borrowProgressMapper.save(borrowProgress);

			// 生成还款计划并授权
			borrowRepayService.genRepayPlan(borrow);
			//发送放款成功短信
			clSmsService.loanInform(borrow.getUserId(), borrow.getId());
		} else {
			logger.error("线下放款失败，当前订单不存在");
			throw new BussinessException("线下放款失败，当前订单不存在");
		}
		return code;
	}

	public Float getModelScore(Long borrowId) {
		String[] featureNameArr = ModelUtil.getFeatureNames();
		Map<String, Object> naiveFeatures = clBorrowMapper.getModelData(borrowId);
		OperatorReport operator = operatorReportMapper.getOperatorReport(borrowId);
		if(operator != null) {
			//运营商报告内容
			String report = operator.getReport();
			//调用工具类解析运营商报告内容
			JSONObject operatorJson = CarrierMxUtils.parse(report);
			//遍历josn对象
			for (String key : operatorJson.keySet()) {
				//根据key获得value,
				String value = operatorJson.getString(key);
				naiveFeatures.put(key, operatorJson.get(key));
			}
			logger.info("运营商数据处理完毕");
		} else {
			JSONObject operatorJSON = getOperatorJSON(borrow_id);
			for (String key : operatorJSON.keySet()) {
				naiveFeatures.put(key, -99999);
			}
			logger.info("订单" + borrowId + "运营商数据为空");
		}
		try {
			if(naiveFeatures != null) {
				Map<String, Float> hashMap = ModelUtil.getCleanedFeatures(naiveFeatures);
				float[] cleanedFeatures = ModelUtil.getFeaturesFromMap(hashMap, featureNameArr);
				int colSize = cleanedFeatures.length;
				DMatrix matrix = new DMatrix(cleanedFeatures, 1, colSize, -9999999f);
				Booster boosterModel = XGBoost.loadModel("/Users/szb/Downloads/ext_fin_v1.model");
				float[] scoreArray = boosterModel.predict(matrix)[0];
				float score = scoreArray[0];
				logger.info("订单" + borrowId + " 模型分值为:" + score);
				return score;
			} else {
				logger.info("订单:" + borrowId + " 不存在对应的变量数据");
				return 0f;
			}
		} catch (Exception e) {
			logger.error("获取订单:" + borrowId + " 变量数据异常");
			return 0f;
		}

	}

}
