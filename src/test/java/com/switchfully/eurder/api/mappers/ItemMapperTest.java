package com.switchfully.eurder.api.mappers;

import com.switchfully.eurder.api.dto.CreateItemDTO;
import com.switchfully.eurder.api.dto.ItemDTO;
import com.switchfully.eurder.domain.entities.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemMapperTest {

    private Item item;
    private ItemDTO itemDTO;
    private CreateItemDTO createItemDTO;
    private ItemMapper itemMapper;

    @BeforeEach
    void setUp() {
        item = Item.ItemBuilder.item()
                .withName("Test Item")
                .withDescription("Test description")
                .withPrice(BigDecimal.valueOf(5))
                .withAmountInStock(5)
                .build();

        itemDTO = ItemDTO.ItemBuilder.item()
                .withName("Test Item")
                .withDescription("Test description")
                .withPrice(BigDecimal.valueOf(5))
                .withAmountInStock(5)
                .build();

        createItemDTO = new CreateItemDTO("Test Item", "TestDescription", BigDecimal.valueOf(5), 5);


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
    @DisplayName("When converted to entity all fields are correct")
    void toEntity() {
        Item result = itemMapper.toEntity(createItemDTO);

        assertEquals(createItemDTO.getName(), result.getName());
        assertEquals(createItemDTO.getDescription(), result.getDescription());
        assertEquals(createItemDTO.getPrice(), result.getPrice());
        assertEquals(createItemDTO.getAmountInStock(), result.getAmountInStock());
    }


}