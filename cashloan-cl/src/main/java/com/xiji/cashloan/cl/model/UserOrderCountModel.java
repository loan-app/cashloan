package com.xiji.cashloan.cl.model;

/**
 * @auther : wnb
 * @date : 2019/3/29
 * @describe :审核人待审核订单数
 */
public class UserOrderCountModel {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 待审核订单数
     */
    private Integer reviewCount;

    /**
     * 审核人名称
     */
    private String userName;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
