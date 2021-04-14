package com.fudai.dal.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * repay_plan_create_record 表实体类
 *
 * @author fudai
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "repay_plan_create_record")
public class RepayPlanCreateRecord {
    @Id
    /**
    * 主键
    */
    private String id;
    /**
    * 请求流水号
    */
    private String reqSerialNo;
    /**
    * 资产id
    */
    private String assetId;
    /**
    * 业务资产id
    */
    private String bizAssetId;
    /**
    * 机构code
    */
    private String orgCode;
    /**
    * 偿还⽅案
    */
    private String repayplan;
    /**
    * 偿还⽅案描述
    */
    private String repayplandesc;
    /**
    * 处置批次号
    */
    private String disposeBatchNo;
    /**
    * 备忘
    */
    private String remark;
    /**
    * 创建时间
    */
    private Date createdTime;
    /**
    * 更新时间
    */
    private Date updatedTime;

}