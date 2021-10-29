package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.domain.entities.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemMapperTest {

    private Item item;
    private CreateItemDTO createItemDTO;
    private UpdateItemDTO updateItemDTO;
    private ItemMapper itemMapper;
    private UUID randomUUID;

    @BeforeEach
    void setUp() {
        item = Item.ItemBuilder.item()
                .withName("Test Item")
                .withDescription("Test description")
                .withPrice(BigDecimal.valueOf(5))
                .withAmountInStock(5)
                .buildNewItem();

        createItemDTO = CreateItemDTO.CreateItemDTOBuilder.item()
                .withName("Test Create Item")
                .withDescription("Test description")
                .withPrice(BigDecimal.valueOf(5))
                .withAmountInStock(5)
                .build();

        randomUUID = UUID.randomUUID();

        updateItemDTO = UpdateItemDTO.UpdateItemDTOBuilder.item()
                .withName("Test Update Item")
                .withDescription("Test description")
                .withPrice(BigDecimal.valueOf(5))
                .withAmountInStock(5)
                .build();

        itemMapper = new ItemMapper();
    }

    @Test
    @DisplayName("When converted to DTO all fields are correct")
    void toDTO() {
        ItemDTO result = itemMapper.toDTO(item);

        assertEquals(item.getId(), result.getId());
        assertEquals(item.getName(), result.getName());
        assertEquals(item.getDescription(), result.getDescription());
        assertEquals(item.getPrice(), result.getPrice());
        assertEquals(item.getAmountInStock(), result.getAmountInStock());
    }

    @Test
    @DisplayName("When converting CreateItemDTO to entity all fields are correct")
    void toEntityForCreateMethod() {
        Item result = itemMapper.toEntity(createItemDTO);

        assertEquals(createItemDTO.getName(), result.getName());
        assertEquals(createItemDTO.getDescription(), result.getDescription());
        assertEquals(createItemDTO.getPrice(), result.getPrice());
        assertEquals(createItemDTO.getAmountInStock(), result.getAmountInStock());
    }

    @Test
    @DisplayName("When converting ItemDTO to entity all fields are correct")
    void toEntityForUpdateMethod() {
        Item result = itemMapper.toEntity(updateItemDTO, randomUUID);

        assertEquals(randomUUID, result.getId());
        assertEquals(updateItemDTO.getName(), result.getName());
        assertEquals(updateItemDTO.getDescription(), result.getDescription());
        assertEquals(updateItemDTO.getPrice(), result.getPrice());
        assertEquals(updateItemDTO.getAmountInStock(), result.getAmountInStock());
    }


}