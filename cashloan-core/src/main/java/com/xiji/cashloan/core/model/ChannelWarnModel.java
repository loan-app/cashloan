package com.xiji.cashloan.core.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelWarnModel {
    private Long channelId;
    private Integer wechatCount;
    private Integer qqCount;
    private Integer weiboCount;
    private Integer otherCount;
    private Double wechatPercent;
    private Double qqPercent;
    private Double weiboPercent;
    private Double otherPercent;
}
