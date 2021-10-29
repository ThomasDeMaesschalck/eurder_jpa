package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.domain.entities.Item;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ItemMapper {


    public ItemDTO toDTO(Item item) {
        return ItemDTO.ItemDTOBuilder.item()
                .withId(item.getId())
                .withName(item.getName())
                .withDescription(item.getDescription())
                .withPrice(item.getPrice())
                .withAmountInStock(item.getAmountInStock())
                .build();
    }

    public List<ItemDTO> toDTO(List<Item> items) {
        return items.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public Item toEntity(CreateItemDTO createItemDTO) {
        return Item.ItemBuilder.item()
                .withName(createItemDTO.getName())
                .withDescription(createItemDTO.getDescription())
                .withPrice(createItemDTO.getPrice())
                .withAmountInStock(createItemDTO.getAmountInStock())
                .buildNewItem();
    }

    public Item toEntity(UpdateItemDTO updateItemDTO, UUID itemUUID) {
        return Item.ItemBuilder.item()
                .withId(itemUUID)
                .withName(updateItemDTO.getName())
                .withDescription(updateItemDTO.getDescription())
                .withPrice(updateItemDTO.getPrice())
                .withAmountInStock(updateItemDTO.getAmountInStock())
                .buildUpdatedNewItem();
    }


}
