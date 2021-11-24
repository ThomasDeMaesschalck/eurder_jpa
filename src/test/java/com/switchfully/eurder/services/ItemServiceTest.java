package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.api.mappers.ItemMapper;
import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.repositories.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class ItemServiceTest {

    private CreateItemDTO createItemDTO;
    private Item item;
    private ItemDTO itemDTOResponse;

    private ItemMapper itemMapperMock;
    private ItemRepository itemRepositoryMock;
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        createItemDTO = CreateItemDTO.builder()
                .name("My Item")
                .description("Fancy item")
                .price(BigDecimal.valueOf(500))
                .amountInStock(5)
                .build();

        itemDTOResponse = ItemDTO.ItemDTOBuilder.item()
                .withId(1L)
                .withName("new name")
                .build();

        item = Item.builder().id(1L).build();

        itemMapperMock = Mockito.mock(ItemMapper.class);
        itemRepositoryMock = Mockito.mock(ItemRepository.class);

        itemService = new ItemService(itemMapperMock, itemRepositoryMock);

    }

    @Test
    @DisplayName("Saving an item results in item saved in repo")
    void whenSavingItem_RepoIsCalled() {
        Mockito.when(itemMapperMock.toEntity(any(CreateItemDTO.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.save(any(Item.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.findAll()).thenReturn(List.of(item));

        itemService.save(createItemDTO);


        int expected = 1;
        int result = itemService.getAllItems().size();

        assertEquals(expected, result);
        Mockito.verify(itemRepositoryMock).save(any(Item.class));
    }

    @Test
    @DisplayName("Updating an item does not increase repo size")
    void whenUpdatingItem_SizeOfRepoIsStillOne() {
        Mockito.when(itemMapperMock.toEntity(any(CreateItemDTO.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.save(any(Item.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(item));
        Mockito.when(itemMapperMock.toEntity(any(UpdateItemDTO.class), any(Long.class))).thenReturn(item);
        Mockito.when(itemMapperMock.toDTO(any(Item.class))).thenReturn(itemDTOResponse);
        Mockito.when(itemRepositoryMock.findAll()).thenReturn(List.of(item));

        itemService.save(createItemDTO);

        Long itemUUID = itemService.getAllItems().get(0).getId();
        String newName = "new name";

        UpdateItemDTO updateItemDTO = new UpdateItemDTO.UpdateItemDTOBuilder()
                .withDescription(createItemDTO.getDescription())
                .withName(newName)
                .withAmountInStock(createItemDTO.getAmountInStock())
                .withPrice(createItemDTO.getPrice())
                .build();

        itemService.update(updateItemDTO, itemUUID);

        int expected = 1;
        int result = itemService.getAllItems().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Updating item name changes item name")
    void whenUpdatingItemName_NameGetsChanged() {
        Mockito.when(itemMapperMock.toEntity(any(CreateItemDTO.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.save(any(Item.class))).thenReturn(item);
        Mockito.when(itemRepositoryMock.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(item));
        Mockito.when(itemMapperMock.toEntity(any(UpdateItemDTO.class), any(Long.class))).thenReturn(item);
        Mockito.when(itemMapperMock.toDTO(any(Item.class))).thenReturn(itemDTOResponse);
        Mockito.when(itemRepositoryMock.findAll()).thenReturn(List.of(item));

        itemService.save(createItemDTO);

        Long itemUUID = itemService.getAllItems().get(0).getId();
        String newName = "new name";

        UpdateItemDTO updateItemDTO = new UpdateItemDTO.UpdateItemDTOBuilder()
                .withDescription(createItemDTO.getDescription())
                .withName(newName)
                .withAmountInStock(createItemDTO.getAmountInStock())
                .withPrice(createItemDTO.getPrice())
                .build();

        itemService.update(updateItemDTO, itemUUID);

        String result = itemService.getAllItems().get(0).getName();

        assertEquals(newName, result);
    }

}