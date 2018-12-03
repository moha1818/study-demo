/*
 * 版权所有(C) 浙江大道网络科技有限公司2011-2020
 * Copyright 2009-2020 Zhejiang GreatTao Factoring Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 * Zhejiang GreatTao Corporation ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Zhejiang GreatTao
 */

package com.moha.demo.entity;


public class OrderInfo {
    private TenantInfo tenantInfo;
    private CustomerInfo customerInfo;
    private DiscountInfo discountInfo;
    private FoodListInfo foodListInfo;
    private OtherInfo otherInfo;

    public OrderInfo(CustomerInfo customerInfo, TenantInfo tenantInfo, DiscountInfo discountInfo, FoodListInfo foodListInfo, OtherInfo otherInfo) {
        this.customerInfo = customerInfo;
        this.tenantInfo = tenantInfo;
        this.discountInfo = discountInfo;
        this.foodListInfo = foodListInfo;
        this.otherInfo = otherInfo;
    }

    public TenantInfo getTenantInfo() {
        return tenantInfo;
    }

    public void setTenantInfo(TenantInfo tenantInfo) {
        this.tenantInfo = tenantInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public DiscountInfo getDiscountInfo() {
        return discountInfo;
    }

    public void setDiscountInfo(DiscountInfo discountInfo) {
        this.discountInfo = discountInfo;
    }

    public FoodListInfo getFoodListInfo() {
        return foodListInfo;
    }

    public void setFoodListInfo(FoodListInfo foodListInfo) {
        this.foodListInfo = foodListInfo;
    }

    public OtherInfo getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(OtherInfo otherInfo) {
        this.otherInfo = otherInfo;
    }
}
