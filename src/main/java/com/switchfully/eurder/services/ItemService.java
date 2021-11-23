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
import java.util.Optional;
import java.util.stream.Collectors;

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


    public ItemDTO save(Long adminId, CreateItemDTO createItemDTO) {
        userService.assertAdminId(adminId);

        Item created = itemRepository.save(itemMapper.toEntity(createItemDTO));
        return itemMapper.toDTO(created);
    }

    public ItemDTO update(Long adminId, UpdateItemDTO updateItemDTO, Long itemId) {
        userService.assertAdminId(adminId);
        assertItemId(itemId);

        Item update = itemRepository.save(itemMapper.toEntity(updateItemDTO, itemId));
        return itemMapper.toDTO(update);
    }

    public List<ItemDTO> getAllItems(Long adminId) {
        userService.assertAdminId(adminId);

        return itemRepository.findAll().stream().map(itemMapper::toDTO).collect(Collectors.toList());
    }

    public Item getById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    public void assertItemId(Long itemId) {
        Optional<Item> itemOptional = itemRepository.findById(itemId);

        if (itemOptional.isEmpty()) {
            throw new IllegalArgumentException("Item id not found");
        }
    }

}
