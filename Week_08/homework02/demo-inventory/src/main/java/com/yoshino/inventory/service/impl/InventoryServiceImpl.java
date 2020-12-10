package com.yoshino.inventory.service.impl;

import com.yoshino.common.inventory.api.InventoryService;
import com.yoshino.common.inventory.dto.InventoryDTO;
import com.yoshino.common.inventory.mapper.InventoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "inventoryService")
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean decrease(InventoryDTO inventoryDTO) {
        log.info("==扣减库存==");
        inventoryMapper.decrease(inventoryDTO);
        return true;
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmMethod", cancelMethod = "cancelMethod")
    public Boolean mockWithTryException(InventoryDTO inventoryDTO) {
        log.info("==扣减库存==");
        if (inventoryDTO != null) {
            log.info("==扣减库存 异常==");
            throw new RuntimeException("mock exception");
        }
        return true;
    }

    public Boolean confirmMethod(InventoryDTO inventoryDTO) {
        log.info("=========扣减库存确认=========");
        inventoryMapper.confirm(inventoryDTO);
        return true;
    }

    public Boolean cancelMethod(InventoryDTO inventoryDTO) {
        log.info("=========扣减库存取消=========");
        inventoryMapper.cancel(inventoryDTO);
        return true;
    }

}
