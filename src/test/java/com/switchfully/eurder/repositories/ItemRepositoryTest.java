package com.switchfully.eurder.repositories;

import com.switchfully.eurder.domain.entities.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestPropertySource(properties = "spring.jpa.properties.hibernate.default_schema=eurder")
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    private Item item1;
    private Item item2;

    @BeforeEach
    void setUp() {

        String name = "Big item";
        String name2 = "Another item";
        String description = "Something in a very large box";
        BigDecimal price = BigDecimal.valueOf(500);
        int amountInStock = 5;

        item1 = Item.builder()
                .name(name)
                .description(description)
                .price(price)
                .amountInStock(amountInStock)
                .build();

        item2 = Item.builder()
                .name(name2)
                .description(description)
                .price(price)
                .amountInStock(amountInStock)
                .build();
    }

    @DisplayName("When saving items they are added to the repository ")
    @Test
    void whenSavingItems_thenRepositorySizeIsAccurate() {
        itemRepository.save(item1);
        itemRepository.save(item2);

        int expected = 2;
        int result = itemRepository.findAll().size();

        assertEquals(expected, result);
    }

    @DisplayName("Saved Item details are found via repository")
    @Test
    void whenSavingItem_thenRepositoryContainsThisItem() {
        itemRepository.save(item1);

        Item result = itemRepository.findAll().get(0);

        assertEquals(item1.getId(), result.getId());
        assertEquals(item1.getName(), result.getName());
        assertEquals(item1.getDescription(), result.getDescription());
        assertEquals(item1.getPrice(), result.getPrice());
        assertEquals(item1.getAmountInStock(), result.getAmountInStock());
    }

    @DisplayName("Get by id returns correct Item")
    @Test
    void whenGettingById_thenUserIsReturned() {
        itemRepository.save(item1);

        Item result = itemRepository.findAll().get(0);
        Long idOfItem = result.getId();

        result = itemRepository.getById(idOfItem);

        assertEquals(item1.getId(), result.getId());
        assertEquals(item1.getName(), result.getName());
        assertEquals(item1.getDescription(), result.getDescription());
        assertEquals(item1.getPrice(), result.getPrice());
        assertEquals(item1.getAmountInStock(), result.getAmountInStock());
    }
}