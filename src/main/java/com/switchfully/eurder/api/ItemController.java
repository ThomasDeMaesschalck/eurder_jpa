package com.switchfully.eurder.api;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.items.UpdateItemDTO;
import com.switchfully.eurder.services.ItemService;
import com.switchsecure.SecurityGuard;
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
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public List<ItemDTO> getAllItems() {
        logger.info("Admin getting all items");
        return itemService.getAllItems();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public ItemDTO createItem(@Valid @RequestBody CreateItemDTO createItemDTO) {
        logger.info("Admin is making new item " + createItemDTO.getName());
        return itemService.save(createItemDTO);
    }

    @PutMapping(path = "{itemId}", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    @SecurityGuard(SecurityGuard.ApiUserRole.ADMIN)
    public ItemDTO updateItem( @PathVariable Long itemId,
                            @Valid @RequestBody UpdateItemDTO itemDTO) {
        logger.info("Admin is updating item with id " + itemId);
        return itemService.update(itemDTO, itemId);
    }
}
