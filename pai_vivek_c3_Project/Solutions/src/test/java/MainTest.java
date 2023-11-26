import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class MainTest {
    @Test
    public void user_searchs_name_coffee_then_it_returns_information_then_exiting() {
        String userInput = System.lineSeparator() + "1" + System.lineSeparator() +
                "Amelie's cafe"+ System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "Restaurant:Amelie's cafe\n" +
                "Location:Chennai\n" +
                "Opening time:10:30\n" +
                "Closing time:22:00\n" +
                "Menu:\n" +
                "[Chicken fried:180, Potato fried:15, Sweet corn soup:119, Vegetable lasagne:269]";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        main.main(null); // call the main method

        Assertions.assertTrue(baos.toString().contains(expected));
    }

    @Test
    public void user_searchs_random_name_coffee_then_it_returns_not_found_then_exiting() {
        String userInput = System.lineSeparator() + "1" + System.lineSeparator() +
                "AAAA"+ System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expected = "restaurantNotFoundException: AAAA";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        main.main(null); // call the main method

        Assertions.assertTrue(baos.toString().contains(expected));
    }

    @Test
    public void user_searchs_name_coffee_then_it_returns_restaurant_then_user_gets_menu_then_choosing_item1_item2_then_show_total_cost() {
        String userInput = System.lineSeparator() +
                "1" + System.lineSeparator() +
                "Amelie's cafe"+ System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "1" + System.lineSeparator() +
                "4" + System.lineSeparator() +
                "5" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expectedRestaurant = "Restaurant:Amelie's cafe\n" +
                "Location:Chennai\n" +
                "Opening time:10:30\n" +
                "Closing time:22:00\n" +
                "Menu:\n" +
                "[Chicken fried:180, Potato fried:15, Sweet corn soup:119, Vegetable lasagne:269]";
        String expectedRestaurantMenu = "Menu: \n" +
                "0. Chicken fried:180\n" +
                "1. Potato fried:15\n" +
                "2. Sweet corn soup:119\n" +
                "3. Vegetable lasagne:269";
        String total = "Total: 195";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        main.main(null); // call the main method

        Assertions.assertTrue(baos.toString().contains(expectedRestaurant));
        Assertions.assertTrue(baos.toString().contains(expectedRestaurantMenu));
        Assertions.assertTrue(baos.toString().contains(total));
    }

    @Test
    public void user_search_name_coffee_then_it_returns_restaurant_then_user_gets_menu_then_removing_item_with_list_choose_is_null_then_showing_exception() {
        String userInput = System.lineSeparator() +
                "1" + System.lineSeparator() +
                "Amelie's cafe"+ System.lineSeparator() +
                "1" + System.lineSeparator() +
                "3" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expectedRestaurant = "Restaurant:Amelie's cafe\n" +
                "Location:Chennai\n" +
                "Opening time:10:30\n" +
                "Closing time:22:00\n" +
                "Menu:\n" +
                "[Chicken fried:180, Potato fried:15, Sweet corn soup:119, Vegetable lasagne:269]";
        String expectedRestaurantMenu = "Menu: \n" +
                "0. Chicken fried:180\n" +
                "1. Potato fried:15\n" +
                "2. Sweet corn soup:119\n" +
                "3. Vegetable lasagne:269";
        String itemNotFound = "itemNotFoundException: Chicken fried";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        main.main(null); // call the main method

        Assertions.assertTrue(baos.toString().contains(expectedRestaurant));
        Assertions.assertTrue(baos.toString().contains(expectedRestaurantMenu));
        Assertions.assertTrue(baos.toString().contains(itemNotFound));
    }

    @Test
    public void user_searchs_name_coffee_then_it_returns_restaurant_then_user_gets_menu_then_choosing_item1_continue_choosing_item1_then_it_returns_exception_then_showing_total_cost() {
        String userInput = System.lineSeparator() +
                "1" + System.lineSeparator() +
                "Amelie's cafe"+ System.lineSeparator() +
                "1" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "2" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "4" + System.lineSeparator() +
                "5" + System.lineSeparator() +
                "0" + System.lineSeparator() +
                "0";
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        String expectedRestaurant = "Restaurant:Amelie's cafe\n" +
                "Location:Chennai\n" +
                "Opening time:10:30\n" +
                "Closing time:22:00\n" +
                "Menu:\n" +
                "[Chicken fried:180, Potato fried:15, Sweet corn soup:119, Vegetable lasagne:269]";
        String expectedRestaurantMenu = "Menu: \n" +
                "0. Chicken fried:180\n" +
                "1. Potato fried:15\n" +
                "2. Sweet corn soup:119\n" +
                "3. Vegetable lasagne:269";
        String expectedExisited = "itemExistedException: Chicken fried";
        String total = "Total: 180";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        main.main(null); // call the main method

        Assertions.assertTrue(baos.toString().contains(expectedRestaurant));
        Assertions.assertTrue(baos.toString().contains(expectedRestaurantMenu));
        Assertions.assertTrue(baos.toString().contains(expectedExisited));
        Assertions.assertTrue(baos.toString().contains(total));
    }

}
