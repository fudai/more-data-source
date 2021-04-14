package com.fudai.dal.dao;

import com.fudai.aop.DataSourceSwitcher;
import com.fudai.dal.entity.RepayPlanCreateRecord;
import com.fudai.dal.mapper.RepayPlanCreateRecordMapper;
import com.fudai.enums.DataSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * 还款方案创建记录表(RepayPlanCreateRecord)dao实现
 *
 * @author fudai
 * @since 2021-04-13 10:13:25
 */
 @Repository
public class RepayPlanCreateRecordDao {

    @Autowired
    private RepayPlanCreateRecordMapper repayPlanCreateRecordMapper;

     /**
     * 插入RepayPlanCreateRecord数据，返回主键ID
     *
     * @param repayPlanCreateRecord 
     * @return id 主键
     */
    public String insertRepayPlanCreateRecord(RepayPlanCreateRecord repayPlanCreateRecord) {
        if (null == repayPlanCreateRecord) {
            return null;
        }
        repayPlanCreateRecordMapper.insertSelective(repayPlanCreateRecord);
        return repayPlanCreateRecord.getId();
    }

    /**
     * id查询数据
     *
     * @param id 主键 
     * @return repayPlanCreateRecord 实例对象
     */
    @DataSourceSwitcher(value = DataSourceEnum.MASTER)
    public Optional<RepayPlanCreateRecord>  getRepayPlanCreateRecordById(String id) {
        RepayPlanCreateRecord repayPlanCreateRecord = repayPlanCreateRecordMapper.selectByPrimaryKey(id);
        if (null == repayPlanCreateRecord) {
            return Optional.empty();
        }
         return Optional.of(repayPlanCreateRecord);
    }

      /**
     * id更新数据
     *
     * @param repayPlanCreateRecord 实例对象
     * @return repayPlanCreateRecord 实例对象 
     */
    public Optional<RepayPlanCreateRecord> updateRepayPlanCreateRecordById(RepayPlanCreateRecord repayPlanCreateRecord) {
        if (null == repayPlanCreateRecord) {
            return Optional.empty();
        }
        repayPlanCreateRecordMapper.updateByPrimaryKeySelective(repayPlanCreateRecord);
        return Optional.of(repayPlanCreateRecord);
    }

}