package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.domain.entities.Item;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return Item.builder()
                .name(createItemDTO.getName())
                .description(createItemDTO.getDescription())
                .price(createItemDTO.getPrice())
                .amountInStock(createItemDTO.getAmountInStock())
                .build();
    }

    public Item toEntity(UpdateItemDTO updateItemDTO, Long itemUUID) {
        return Item.builder()
                .id(itemUUID)
                .name(updateItemDTO.getName())
                .description(updateItemDTO.getDescription())
                .price(updateItemDTO.getPrice())
                .amountInStock(updateItemDTO.getAmountInStock())
                .build();
    }


}
