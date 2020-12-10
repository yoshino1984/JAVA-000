package com.yoshino.common.account.api;

import com.yoshino.common.account.dto.AccountDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author wangxin
 * 2020/12/10 上午12:29
 * @since
 **/
public interface AccountService {

    /**
     * 扣款支付
     *
     * @param accountDTO 参数dto
     */
    @Hmily
    Boolean payment(AccountDTO accountDTO);
}
