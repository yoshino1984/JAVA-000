package com.yoshino.account.service.impl;

import com.yoshino.common.account.api.AccountService;
import com.yoshino.common.account.dto.AccountDTO;
import com.yoshino.common.account.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        log.info("=========下单扣减账户=========");
        accountMapper.update(accountDTO);
        return false;
    }

    public Boolean confirmMethod(AccountDTO accountDTO) {
        log.info("=========扣减账户确认=========");
        accountMapper.confirm(accountDTO);
        return true;
    }

    public Boolean cancelMethod(AccountDTO accountDTO) {
        log.info("=========扣减账户取消=========");
        accountMapper.cancel(accountDTO);
        return true;
    }

}
