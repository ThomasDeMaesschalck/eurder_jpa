package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.Item;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ItemRepository {

    private final Map<UUID, Item> items = new HashMap<>();

    public ItemRepository() {
    }

    public Item save(Item item) {
        items.put(item.getId(), item);
        return items.get(item.getId());
    }

    public List<Item> getItems() {
        return List.copyOf(items.values());
    }

    public Item getById(UUID itemId) {
        Item item = items.get(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Item with id " + itemId + " not found.");
        }
        return item;
    }
}
