package com.yoshino.homework01.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Order {
    private Long id;

    private String orderNo;

    private Long shopId;

    private Long userId;

    private Long countryId;

    private Long provinceId;

    private Long cityId;

    private Long districtId;

    private Long streetId;

    private String address;

    private String phone;

    private String money;

    private String remark;

    private String status;

    private String isDeleted;

    private Date createTime;

    private Long createBy;

    private Long updateTime;

    private Long updateBy;

}