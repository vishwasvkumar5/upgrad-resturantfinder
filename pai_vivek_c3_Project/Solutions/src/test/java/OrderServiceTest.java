import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class OrderServiceTest {
    @InjectMocks
    OrderService orderService;

    Restaurant restaurant;

    @BeforeEach
    public void init() {
        initValue();
    }

    @Test
    public void add_items_into_list_order_return_expected_prices() {
        List<Item> menu = restaurant.getMenu();

        Assertions.assertDoesNotThrow(() -> {
            orderService.chooseItem(menu.get(0));
            orderService.chooseItem(menu.get(1));

            Assertions.assertEquals(2, orderService.getChooses().size());
            Assertions.assertEquals("Chicken fried:180", orderService.getChooses().get(0).toString());
            Assertions.assertEquals("Potato fried:15", orderService.getChooses().get(1).toString());
            Assertions.assertEquals(195, orderService.total());
        });
    }


    @Test
    public void add_items_into_list_but_it_existed_return_expected_exception() {
        List<Item> menu = restaurant.getMenu();

        Assertions.assertThrows(itemExistedException.class, () -> {
            orderService.chooseItem(menu.get(0));
            orderService.chooseItem(menu.get(0));

            Assertions.assertEquals(1, orderService.getChooses().size());
            Assertions.assertEquals("Chicken fried:180", orderService.getChooses().get(0).toString());
        });
    }

    @Test
    public void unchoose_items_into_list_order_return_expected_prices() {
        List<Item> menu = restaurant.getMenu();

        Assertions.assertDoesNotThrow(() -> {
            orderService.chooseItem(menu.get(0));
            orderService.chooseItem(menu.get(1));

            Assertions.assertEquals(2, orderService.getChooses().size());
            Assertions.assertEquals("Chicken fried:180", orderService.getChooses().get(0).toString());
            Assertions.assertEquals("Potato fried:15", orderService.getChooses().get(1).toString());
            Assertions.assertEquals(195, orderService.total());

            orderService.unChooseItem(menu.get(1));
            Assertions.assertEquals(1, orderService.getChooses().size());
            Assertions.assertEquals("Chicken fried:180", orderService.getChooses().get(0).toString());
            Assertions.assertEquals(180, orderService.total());
        });
    }

    @Test
    public void unchoose_items_into_list_order_but_it_existed_return_expected_exception() {
        List<Item> menu = restaurant.getMenu();

        Assertions.assertThrows(itemNotFoundException.class, () -> {
            orderService.unChooseItem(menu.get(0));
        });
    }

    public void initValue() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Chicken fried", 180);
        restaurant.addToMenu("Potato fried", 15);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
}
