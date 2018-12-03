package com.rongdu.cashloan.cl.domain;

import java.io.Serializable;

/**
 * 广告实体
 * 
 * @author wmc
 * @version 1.0.0
 * @date 2017-06-21 14:33:20
 * Copyright 杭州融都科技股份有限公司  cashloan All Rights Reserved
 * 官方网站：www.erongdu.com
 * 未经授权不得进行修改、复制、出售及商业使用
 */
 public class Adver implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 主键Id
    */
    private Long id;

    /**
    * 标题
    */
    private String title;

    /**
    * 路径
    */
    private String path;

    /**
    * 排序号
    */
    private int sort;

    /**
    * 状态 10启用 20禁用
    */
    private String state;
    
    /**
     * 链接
     */
    private String link;


    /**
    * 获取主键Id
    *
    * @return id
    */
    public Long getId(){
        return id;
    }

    /**
    * 设置主键Id
    * 
    * @param 要设置的主键Id
    */
    public void setId(Long id){
        this.id = id;
    }

    /**
    * 获取标题
    *
    * @return title
    */
    public String getTitle(){
        return title;
    }

    /**
    * 设置标题
    * 
    * @param title 要设置的标题
    */
    public void setTitle(String title){
        this.title = title;
    }

    /**
    * 获取路径
    *
    * @return path
    */
    public String getPath(){
        return path;
    }

    /**
    * 设置路径
    * 
    * @param path 要设置的路径
    */
    public void setPath(String path){
        this.path = path;
    }
    
    /**
	 * @return the 排序号
	 */
	public int getSort() {
		return sort;
	}

	/**
	 * @param 排序号 the sort to set
	 */
	public void setSort(int sort) {
		this.sort = sort;
	}

	/**
    * 获取状态 10-启用 20-禁用
    *
    * @return state
    */
    public String getState(){
        return state;
    }

    /**
    * 设置状态 10-启用 20-禁用
    * 
    * @param state 要设置的状态 10-启用 20-禁用
    */
    public void setState(String state){
        this.state = state;
    }

	/**
	 * 获取链接
	 * @return link
	 */
	public String getLink() {
		return link;
	}

	/**
	 * 设置链接
	 * @param link
	 */
	public void setLink(String link) {
		this.link = link;
	}

}