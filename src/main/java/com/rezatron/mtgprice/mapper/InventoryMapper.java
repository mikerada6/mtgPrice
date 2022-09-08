package com.rezatron.mtgprice.mapper;

import com.rezatron.mtgprice.dto.InventoryDto;
import com.rezatron.mtgprice.inventory.Inventory;
import com.rezatron.mtgprice.service.CardService;
import com.rezatron.mtgprice.service.UserService;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper( unmappedTargetPolicy = ReportingPolicy.IGNORE,
         componentModel = "spring",
         uses = {CardService.class, UserService.class} )
public
interface InventoryMapper {
    @Mapping( source = "cardId",
              target = "card")
    @Mapping( source = "userId",
              target = "user" )
    Inventory inventoryDtoToInventory(InventoryDto inventoryDto);

    @Mapping( source = "card.id",
              target = "cardId" )
    @Mapping( source = "user.id",
              target = "userId" )
    @InheritInverseConfiguration( name = "inventoryDtoToInventory" )
    InventoryDto inventoryToInventoryDto(Inventory inventory);

    @InheritConfiguration( name = "inventoryDtoToInventory" )
    @BeanMapping( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
    Inventory updateInventoryFromInventoryDto(InventoryDto inventoryDto,
                                              @MappingTarget
                                              Inventory inventory);
}
