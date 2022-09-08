package com.rezatron.mtgprice.service;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.inventory.Inventory;
import com.rezatron.mtgprice.mapper.InventoryMapper;
import com.rezatron.mtgprice.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public
class InventoryService {

    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    InventoryRepository inventoryRepository;

    public
    InventoryDto addCard(InventoryDto dto) {
        Inventory inventory = inventoryMapper.inventoryDtoToInventory( dto );
        if(inventory.getCard() != null && inventory.getUser()!=null)
        {
            inventory= inventoryRepository.saveAndFlush(inventory);
            return inventoryMapper.inventoryToInventoryDto(inventory  );
        }
        return null;
    }
}
