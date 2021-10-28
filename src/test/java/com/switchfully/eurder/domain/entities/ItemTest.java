package com.switchfully.eurder.domain.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {


    @Test
    @DisplayName("When creating a valid Item no exception is thrown")
    void whenValidItemMade_thenDoesNotThrowException() {
        assertDoesNotThrow(() -> Item.ItemBuilder.item()
                .withName("Box")
                .withDescription("A fancy box")
                .withPrice(BigDecimal.valueOf(500))
                .withAmountInStock(5)
                .buildNewItem()
        );
    }


    @Test
    @DisplayName("When creating Item with invalid name an exception is thrown ")
    void whenItemMadeWithInvalidName_ExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName(null)
                        .withDescription("A fancy box")
                        .withPrice(BigDecimal.valueOf(500))
                        .withAmountInStock(5)
                        .buildNewItem());

        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("")
                        .withDescription("A fancy box")
                        .withPrice(BigDecimal.valueOf(500))
                        .withAmountInStock(5)
                        .buildNewItem());
    }

    @Test
    @DisplayName("When creating Item with invalid description an exception is thrown ")
    void whenItemMadeWithInvalidDescription_ExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("Another box")
                        .withDescription(null)
                        .withPrice(BigDecimal.valueOf(500))
                        .withAmountInStock(5)
                        .buildNewItem());

        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("Another box")
                        .withDescription("")
                        .withPrice(BigDecimal.valueOf(500))
                        .withAmountInStock(5)
                        .buildNewItem());
    }

    @Test
    @DisplayName("When creating Item with invalid price an exception is thrown ")
    void whenItemMadeWithInvalidPrice_ExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("Another box")
                        .withDescription("kljlkjlk")
                        .withPrice(null)
                        .withAmountInStock(5)
                        .buildNewItem());

        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("Another box")
                        .withDescription("kljkljlk")
                        .withPrice(BigDecimal.valueOf(-5))
                        .withAmountInStock(5)
                        .buildNewItem());
    }

    @Test
    @DisplayName("When creating Item with invalid stock amount an exception is thrown ")
    void whenItemMadeWithInvalidStock_ExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                Item.ItemBuilder.item()
                        .withName("Another box")
                        .withDescription("")
                        .withPrice(BigDecimal.valueOf(500))
                        .withAmountInStock(-5)
                        .buildNewItem());
    }

    @Test
    @DisplayName("When creating a valid Item it gets a unique ID")
    void whenValidItemMade_thenItHasUUID() {
        Item myitem = Item.ItemBuilder.item()
                .withName("Box")
                .withDescription("A fancy box")
                .withPrice(BigDecimal.valueOf(500))
                .withAmountInStock(5)
                .buildNewItem();

        assertFalse(myitem.getId().toString().isBlank());
    }

}