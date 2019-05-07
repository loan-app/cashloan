package com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.querymock.vo;

import com.xiji.cashloan.cl.model.pay.kuaiqian.payfor.paymock.vo.SealDataType;

import javax.xml.bind.annotation.*;

/**
 * 报文实体
 * @author zan.liang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)  
@XmlRootElement  
@XmlType(name = "searchRequestBody", propOrder = {"sealDataType"})  
public class SearchRequestBody {

	
	@XmlElement(name = "sealDataType")  
	private SealDataType sealDataType;

	public SealDataType getSealDataType() {
		return sealDataType;
	}

	public void setSealDataType(SealDataType sealDataType) {
		this.sealDataType = sealDataType;
	}


	
}
