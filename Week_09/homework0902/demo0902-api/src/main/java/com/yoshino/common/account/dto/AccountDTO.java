/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yoshino.common.account.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The type Account dto.
 */
@Data
@Builder
public class AccountDTO implements Serializable {

    private static final long serialVersionUID = 7223470850578998427L;

    public static final String TYPE_DOLLAR = "dollar";

    public static final String TYPE_RMB = "rmb";

    /**
     * 用户id
     */
    private String userId;

    /**
     * 金额：美元
     */
    private BigDecimal amount;

    /**
     * 账户类型
     */
    private String type;

    /**
     * 是否支付美元的一方
     */
    public boolean isDollarType() {
        return TYPE_DOLLAR.equals(type);
    }

}
