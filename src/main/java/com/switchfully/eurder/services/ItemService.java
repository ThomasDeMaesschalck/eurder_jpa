package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.api.mappers.ItemMapper;
import com.switchfully.eurder.domain.entities.Item;
import com.switchfully.eurder.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemService {

    private final ItemMapper itemMapper;
    private final UserService userService;
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemMapper itemMapper, UserService userService, ItemRepository itemRepository) {
        this.itemMapper = itemMapper;
        this.userService = userService;
        this.itemRepository = itemRepository;
    }


    public ItemDTO save(UUID adminId, CreateItemDTO createItemDTO) {
        userService.assertAdminId(adminId);

        Item created = itemRepository.save(itemMapper.toEntity(createItemDTO));
        return itemMapper.toDTO(created);
    }

    public ItemDTO update(UUID adminId, UpdateItemDTO updateItemDTO, UUID itemId) {
        userService.assertAdminId(adminId);
        assertItemId(itemId);

        Item update = itemRepository.save(itemMapper.toEntity(updateItemDTO,itemId ));
        return itemMapper.toDTO(update);
    }

    public List<ItemDTO> getAllItems(UUID adminId) {
        userService.assertAdminId(adminId);

        return itemMapper.toDTO(itemRepository.getItems());
    }

    public Item getById(UUID itemId){
        return itemRepository.getById(itemId);
    }

    public void assertItemId(UUID itemId) {
        try {
            itemRepository.getById(itemId);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Not possible: " + exception.getMessage());
        }
    }

}
