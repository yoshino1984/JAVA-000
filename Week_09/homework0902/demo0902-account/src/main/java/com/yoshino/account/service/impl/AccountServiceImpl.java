package com.yoshino.account.service.impl;

import com.yoshino.common.account.api.AccountService;
import com.yoshino.common.account.dto.AccountDTO;
import com.yoshino.common.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangxin
 * 2020/12/10 上午11:54
 * @since
 **/
@Service(value = "accountService")
@Slf4j
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean payment(AccountDTO accountDTO) {
        if (accountDTO.isDollarType()) {
            log.info("=========下单扣减美元账户=========" + accountDTO.getUserId());
            accountMapper.paymentDollar(accountDTO);
        } else {
            log.info("=========下单扣减rmb账户=========" + accountDTO.getUserId());
            accountMapper.paymentRmb(accountDTO);
        }
        return false;
    }

    @Transactional()
    public Boolean confirmMethod(AccountDTO accountDTO) {
        if (accountDTO.isDollarType()) {
            log.info("=========扣减美元账户确认=========" + accountDTO.getUserId());
            accountMapper.confirmDollar(accountDTO);
            accountMapper.addBalanceRmb(accountDTO);
        } else {
            log.info("=========扣减rmb账户确认=========" + accountDTO.getUserId());
            accountMapper.confirmRmb(accountDTO);
            accountMapper.addBalanceDollar(accountDTO);
        }
        return true;
    }

    public Boolean cancelMethod(AccountDTO accountDTO) {
        if (accountDTO.isDollarType()) {
            log.info("=========扣减美元账户取消=========" + accountDTO.getUserId());
            accountMapper.cancelDollar(accountDTO);
        } else {
            log.info("=========扣减rmb账户取消=========" + accountDTO.getUserId());
            accountMapper.cancelRmb(accountDTO);
        }
        return true;
    }

}
