package com.switchfully.eurder.services;

import com.switchfully.eurder.api.dto.items.CreateItemDTO;
import com.switchfully.eurder.api.dto.items.ItemDTO;
import com.switchfully.eurder.api.dto.orders.CreateOrderDTO;
import com.switchfully.eurder.api.dto.orders.CreateOrderlineDTO;
import com.switchfully.eurder.api.dto.orders.OrderDTO;
import com.switchfully.eurder.api.dto.orders.OrderlineDTO;
import com.switchfully.eurder.api.dto.users.CreateUserDTO;
import com.switchfully.eurder.api.dto.users.UserDTO;
import com.switchfully.eurder.api.mappers.ItemMapper;
import com.switchfully.eurder.api.mappers.OrderMapper;
import com.switchfully.eurder.api.mappers.OrderlineMapper;
import com.switchfully.eurder.api.mappers.UserMapper;
import com.switchfully.eurder.domain.entities.Orderline;
import com.switchfully.eurder.repositories.ItemRepository;
import com.switchfully.eurder.repositories.OrderRepository;
import com.switchfully.eurder.repositories.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderServiceTest {

    private CreateOrderDTO createOrderDTO;
    private OrderService orderService;
    private UserDTO user;
    private UUID item1UUID;
    private UUID item2UUID;
    private OrderRepository orderRepository;
    private CreateOrderlineDTO createOrderlineDTO1;
    private CreateOrderlineDTO createOrderlineDTO2;

    @BeforeEach
    void setUp() {
        CreateUserDTO userDTO = new CreateUserDTO("firstname", "lastname", "email@email.com", "address", "123456");
        CreateUserDTO adminDTO = new CreateUserDTO("admin", "lastname", "email@email.com", "address", "123456");
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService(new UserMapper(), userRepository);
        orderRepository = new OrderRepository();
        ItemRepository itemRepository = new ItemRepository();

        ItemService itemService = new ItemService(new ItemMapper(), userService, itemRepository);
        orderService = new OrderService(new OrderMapper(), new OrderlineMapper(), userService, itemService, orderRepository);
        userService = new UserService(new UserMapper(), userRepository);
        UserDTO admin = userService.createAdmin(adminDTO);
        user = userService.createUser(userDTO);

        CreateItemDTO createItemDTO = new CreateItemDTO.CreateItemDTOBuilder()
                .withName("My Item")
                .withDescription("Fancy item")
                .withPrice(BigDecimal.valueOf(10))
                .withAmountInStock(5)
                .build();

        CreateItemDTO createItemDTO2 = new CreateItemDTO.CreateItemDTOBuilder()
                .withName("My Item2")
                .withDescription("Fancy item")
                .withPrice(BigDecimal.valueOf(500))
                .withAmountInStock(10)
                .build();

        itemService = new ItemService(new ItemMapper(), userService, itemRepository);

        itemService.save(admin.getId(), createItemDTO);
        itemService.save(admin.getId(), createItemDTO2);

        Optional<ItemDTO> itemDTO1 = itemService.getAllItems(admin.getId()).stream().filter(item -> item.getName().equals("My Item")).findFirst();
        Optional<ItemDTO> itemDTO2 = itemService.getAllItems(admin.getId()).stream().filter(item -> item.getName().equals("My Item2")).findFirst();

        itemDTO1.ifPresent(itemDTO -> item1UUID = itemDTO.getId());

        itemDTO2.ifPresent(itemDTO -> item2UUID = itemDTO.getId());

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
    void whenMakingValidOrder_theOrderInRepoContainsCustomerUUID() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO2));

        orderService.save(user.getId(), createOrderDTO);

        UUID expected = user.getId();
        UUID result = orderRepository.getOrders().get(0).getCustomerId();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("When valid order made then item UUIDs found in repo")
    void whenMakingValidOrder_theOrderInRepoContainsItemUUIDs() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO2));

        orderService.save(user.getId(), createOrderDTO);

        List<UUID> expected = List.of(item1UUID, item2UUID);
        List<UUID> result = orderRepository.getOrders().get(0).getOrderlines().stream().map(Orderline::getItemId).collect(Collectors.toList());

        Assertions.assertThat(expected).hasSameElementsAs(result);
    }

    @Test
    @DisplayName("When valid order made order total is correct")
    void whenMakingValidOrder_theOrderTotalIsCalculatedCorrectly() {
        createOrderDTO = new CreateOrderDTO(List.of(createOrderlineDTO1, createOrderlineDTO2));

        OrderDTO orderDTO = orderService.save(user.getId(), createOrderDTO);

        BigDecimal expected = BigDecimal.valueOf(5050);
        BigDecimal result = orderDTO.getTotalOrderPrice();

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("When valid order made with orderline fully in stock the orderline ships tomorrow")
    void whenMakingValidOrder_thenOrderlineShipsTomorrowWhenItemIsInStock() {
        CreateOrderlineDTO withSufficientStock = new CreateOrderlineDTO(item2UUID, 1);

        createOrderDTO = new CreateOrderDTO(List.of(withSufficientStock));

        OrderDTO orderDTO = orderService.save(user.getId(), createOrderDTO);

        LocalDate expected = LocalDate.now().plusDays(1);
        Optional<OrderlineDTO> orderlineDTO = orderDTO.getOrderlineDTOSet().stream().findFirst();
        LocalDate result = null;

        if (orderlineDTO.isPresent()) {
            result = orderlineDTO.get().getShippingDate();
        }

        assertEquals(expected, result);
    }

    @Test
    @DisplayName("When valid order made with orderline not fully in stock the orderline ships in 7 days")
    void whenMakingValidOrder_thenOrderlineShipsInSevenDaysWhenItemIsNotFullyInStock() {
        CreateOrderlineDTO withSufficientStock = new CreateOrderlineDTO(item2UUID, 1000);

        createOrderDTO = new CreateOrderDTO(List.of(withSufficientStock));

        OrderDTO orderDTO = orderService.save(user.getId(), createOrderDTO);

        LocalDate expected = LocalDate.now().plusDays(7);

        LocalDate result = null;
        Optional<OrderlineDTO> orderlineDTO = orderDTO.getOrderlineDTOSet().stream().findFirst();

        if (orderlineDTO.isPresent()) {
            result = orderlineDTO.get().getShippingDate();
        }

        assertEquals(expected, result);
    }

}