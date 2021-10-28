package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.CreateItemDTO;
import com.switchfully.eurder.api.dto.CreateUserDTO;
import com.switchfully.eurder.api.dto.UpdateItemDTO;
import com.switchfully.eurder.api.dto.UserDTO;
import com.switchfully.eurder.api.mappers.ItemMapper;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.repositories.ItemRepository;
import com.switchfully.eurder.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ItemServiceTest {

    private CreateItemDTO createItemDTO;
    private ItemService itemService;
    private UserDTO admin;
    private UserDTO user;

    @BeforeEach
    void setUp() {
        createItemDTO = new CreateItemDTO.CreateItemDTOBuilder()
                .withName("My Item")
                .withDescription("Fancy item")
                .withPrice(BigDecimal.valueOf(500))
                .withAmountInStock(5)
                .build();

        UserService userService = new UserService(new UserMapper(), new UserRepository());
        itemService = new ItemService(new ItemMapper(), userService, new ItemRepository());

        CreateUserDTO adminDTO = new CreateUserDTO("firstname", "lastname", "email@email.com", "address", "123456");
        CreateUserDTO userDTO = new CreateUserDTO("firstnameUser", "lastname", "email@email.com", "address", "123456");

        admin = userService.saveAdmin(adminDTO);
        user = userService.saveUser(userDTO);
    }

    @Test
    @DisplayName("Saving an item results in item saved in repo")
    void whenSavingItem_SizeOfRepoIsOne() {
        itemService.save(admin.getId(), createItemDTO);

        int expected = 1;
        int result = itemService.getAllItems(admin.getId()).size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Regular user can not save item")
    void whenSavingItemAsUser_ExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> itemService.save(user.getId(), createItemDTO));
    }

    @Test
    @DisplayName("Only admin can get all items list")
    void whenRetrievingListOFItems_ExceptionThrownForUserButAdminGetsAccess() {
        assertThrows(IllegalArgumentException.class, () -> itemService.getAllItems(user.getId()));
        assertDoesNotThrow(() -> itemService.getAllItems(admin.getId()));
    }

    @Test
    @DisplayName("Updating an item does not increase repo size")
    void whenUpdatingItem_SizeOfRepoIsStillOne() {
        itemService.save(admin.getId(), createItemDTO);

        UUID itemUUID = itemService.getAllItems(admin.getId()).get(0).getId();
        String newName = "new name";

        UpdateItemDTO updateItemDTO = new UpdateItemDTO.UpdateItemDTOBuilder()
                .withDescription(createItemDTO.getDescription())
                .withName(newName)
                .withAmountInStock(createItemDTO.getAmountInStock())
                .withPrice(createItemDTO.getPrice())
                .build();

        itemService.update(admin.getId(), updateItemDTO, itemUUID);

        int expected = 1;
        int result = itemService.getAllItems(admin.getId()).size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Updating item name changes item name")
    void whenUpdatingItemName_NameGetsChanged() {
        itemService.save(admin.getId(), createItemDTO);

        UUID itemUUID = itemService.getAllItems(admin.getId()).get(0).getId();
        String newName = "new name";

        UpdateItemDTO updateItemDTO = new UpdateItemDTO.UpdateItemDTOBuilder()
                .withDescription(createItemDTO.getDescription())
                .withName(newName)
                .withAmountInStock(createItemDTO.getAmountInStock())
                .withPrice(createItemDTO.getPrice())
                .build();

        itemService.update(admin.getId(), updateItemDTO, itemUUID);

        String result = itemService.getAllItems(admin.getId()).get(0).getName();

        assertEquals(newName, result);
    }

}