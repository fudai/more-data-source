package com.fudai.dal.dao;

import com.fudai.Bootstrap;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @className: RepayPlanCreateRecordDaoTest
 * @description:
 * @author: fudai
 * @date: 2021-04-13 10:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Bootstrap.class)
public class RepayPlanCreateRecordDaoTest {

    @Autowired
    private RepayPlanCreateRecordDao repayPlanCreateRecordDao;

    @org.junit.Test
    public void getRepayPlanCreateRecordById() {
        Assert.assertTrue(repayPlanCreateRecordDao.getRepayPlanCreateRecordById("1").isPresent());;
    }
}