package com.yoshino.common.inventory.api;

import com.yoshino.common.inventory.dto.InventoryDTO;
import org.dromara.hmily.annotation.Hmily;

/**
 * @author wangxin
 * 2020/12/10 上午12:29
 * @since
 **/
public interface InventoryService {

    /**
     * 扣减库存操作
     * 这一个tcc接口
     *
     * @param inventoryDTO 库存DTO对象
     * @return true boolean
     */
    @Hmily
    Boolean decrease(InventoryDTO inventoryDTO);

    /**
     * mock扣减库存异常
     *
     * @param inventoryDTO dto对象
     * @return String string
     */
    @Hmily
    Boolean mockWithTryException(InventoryDTO inventoryDTO);

}
