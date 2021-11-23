package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.services.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/items")
public class ItemController {

    private final ItemService itemService;
    private final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ItemDTO> getAllItems(@RequestHeader(value = "adminId") Long adminId) {
        logger.info("Admin with id " + adminId + " getting all items");
        return itemService.getAllItems(adminId);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ItemDTO createItem(@RequestHeader(value = "adminId") Long adminId,
                              @Valid @RequestBody CreateItemDTO createItemDTO) {
        logger.info("Admin with id " + adminId + " is making new item " + createItemDTO.getName());
        return itemService.save(adminId, createItemDTO);
    }

    @PutMapping(path = "{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public ItemDTO updateItem(@RequestHeader(value = "adminId") Long adminId, @PathVariable Long itemId,
                            @Valid @RequestBody UpdateItemDTO itemDTO) {
        logger.info("Admin with id " + adminId + " is updating item with id " + itemId);
        return itemService.update(adminId, itemDTO, itemId);
    }
}
