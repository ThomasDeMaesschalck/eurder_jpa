package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.orders.CreateOrderDTO;
import com.switchfully.eurder.api.dto.orders.CreateOrderlineDTO;
import com.switchfully.eurder.api.dto.orders.OrderlineDTO;
import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.api.mappers.ItemMapper;
import com.switchfully.eurder.api.mappers.OrderMapper;
import com.switchfully.eurder.api.mappers.OrderlineMapper;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.repositories.ItemRepository;
import com.switchfully.eurder.repositories.OrderRepository;
import com.switchfully.eurder.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private CreateOrderDTO createOrderDTO;
    private OrderlineDTO orderlineDTO;
    private OrderlineDTO orderlineDTO2;
    private OrderService orderService;
    private UserService userService;
    private CreateUserDTO userDTO;
    private CreateUserDTO adminDTO;
    private UserDTO user;
    private UserDTO admin;
    private CreateItemDTO createItemDTO;
    private CreateItemDTO createItemDTO2;
    private ItemService itemService;
    private UUID item1UUID;
    private UUID item2UUID;
    private OrderRepository orderRepository;
    private CreateOrderlineDTO createOrderlineDTO1;
    private CreateOrderlineDTO createOrderlineDTO2;
    private UserRepository userRepository;
    private ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        userDTO = new CreateUserDTO("firstname", "lastname", "email@email.com", "address", "123456");
        adminDTO = new CreateUserDTO("admin", "lastname", "email@email.com", "address", "123456");
        userRepository = new UserRepository();
        userService = new UserService(new UserMapper(), userRepository);
        orderRepository = new OrderRepository();
        itemRepository = new ItemRepository();

        itemService = new ItemService(new ItemMapper(), userService, itemRepository);
        orderService = new OrderService(new OrderMapper(), new OrderlineMapper(), userService, itemService, orderRepository);
        userService = new UserService(new UserMapper(), userRepository);
        admin = userService.createAdmin(adminDTO);
        user = userService.createUser(userDTO);

        createItemDTO = new CreateItemDTO.CreateItemDTOBuilder()
                .withName("My Item")
                .withDescription("Fancy item")
                .withPrice(BigDecimal.valueOf(10))
                .withAmountInStock(5)
                .build();

        createItemDTO2 = new CreateItemDTO.CreateItemDTOBuilder()
                .withName("My Item2")
                .withDescription("Fancy item")
                .withPrice(BigDecimal.valueOf(500))
                .withAmountInStock(10)
                .build();

        itemService = new ItemService(new ItemMapper(), userService, itemRepository);

        itemService.save(admin.getId(), createItemDTO);
        itemService.save(admin.getId(), createItemDTO2);

        item1UUID = itemService.getAllItems(admin.getId()).stream().filter(item -> item.getName().equals("My Item")).findFirst().get().getId();
        item2UUID = itemService.getAllItems(admin.getId()).stream().filter(item -> item.getName().equals("My Item2")).findFirst().get().getId();

        createOrderlineDTO1 = new CreateOrderlineDTO(item1UUID, 5);
        createOrderlineDTO2 = new CreateOrderlineDTO(item2UUID, 10);
    }

    @Test
    @DisplayName("Order must have existing customer id")
    void whenMakingOrderWithInvalidCustomerId_ExceptionIsThrown() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO1));

        assertThrows(IllegalArgumentException.class, () -> orderService.save(UUID.randomUUID(), createOrderDTO));
    }

    @Test
    @DisplayName("Order must have orderlines with existing Item UUIDs")
    void whenMakingOrderWithInvalidItemUUID_ExceptionIsThrown() {
        CreateOrderlineDTO invalidItemID = new CreateOrderlineDTO(UUID.randomUUID(), 5);
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, invalidItemID));

        assertThrows(IllegalArgumentException.class, () -> orderService.save(user.getId(), createOrderDTO));
    }

    @Test
    @DisplayName("Orderline amount ordered can't be zero")
    void whenMakingOrderWithInvalidAmountOrdered_ExceptionIsThrown() {
        CreateOrderlineDTO invalidOrderlineAmount = new CreateOrderlineDTO(UUID.randomUUID(), 0);
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, invalidOrderlineAmount));

        assertThrows(IllegalArgumentException.class, () -> orderService.save(user.getId(), createOrderDTO));
    }

    @Test
    @DisplayName("Order must have orderlines with amount greater than zero")
    void whenMakingOrderWithNegativeAmountOrdered_ExceptionIsThrown() {
        CreateOrderlineDTO invalidOrderlineAmount = new CreateOrderlineDTO(UUID.randomUUID(), -1);
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, invalidOrderlineAmount));

        assertThrows(IllegalArgumentException.class, () -> orderService.save(user.getId(), createOrderDTO));
    }

    @Test
    @DisplayName("When valid order made repo size increases to one")
    void whenMakingValidOrder_thenSizeOfRepoIncreases() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO2));

        orderService.save(user.getId(), createOrderDTO);

        int expected = 1;
        int result = orderRepository.getOrders().size();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("When valid order made then user id is found in repo")
    void whenMakingValidOrder_theOrderInRepoContainsItemUUIDs() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO2));

        orderService.save(user.getId(), createOrderDTO);

        UUID expected = user.getId();
        UUID result = orderRepository.getOrders().get(0).getCustomerId();

        assertEquals(expected, result);
    }

}