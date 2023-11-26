import java.time.LocalTime;
import java.util.Scanner;

public class main {

    private static RestaurantService restaurantService = new RestaurantService();
    private static OrderService oderService = new OrderService();

    private static void initData() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Chicken fried", 180);
        restaurant.addToMenu("Potato fried", 15);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurantService.addRestaurant(restaurant);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initData();
        while(true) {
            System.out.println("----------------------");
            System.out.println("--RESTAURANT-FINDER---");
            System.out.println("----------------------");
            System.out.println("1. Search restaurant");
            System.out.println("0. Exit");
            System.out.println("Input your choice: ");
            switch (sc.nextLine()) {
                case "1" -> {
                    System.out.println("Input the restaurant name: ");
                    initForSearch(sc);
                }
                case "0" -> { return; }
                default -> {
                }
            }
        }
    }
    private static void initForSearch(Scanner sc) {
        String str = sc.nextLine();
        try {
            Restaurant restaurant = restaurantService.findRestaurantByName(str);
            restaurant.displayDetails();
            while(true) {
                System.out.println("----------------------");
                System.out.println("--" + restaurant.getName().toUpperCase() + "---");
                System.out.println("----------------------");
                System.out.println("1. Get menu");
                System.out.println("2. Add item");
                System.out.println("3. Remove item");
                System.out.println("4. Get list ");
                System.out.println("5. Get total");
                System.out.println("0. Exit");
                System.out.println("Input your choice: ");
                try {
                    switch (sc.nextLine()) {
                        case "1" -> {
                            System.out.println("Menu: ");
                            for (int i = 0; i < restaurant.getMenu().size(); i++) {
                                System.out.println(i+ ". " +restaurant.getMenu().get(i).toString());
                            }
                        }
                        case "2" -> {
                            System.out.println("Add item: ");
                            oderService.chooseItem(restaurant.getMenu().get(Integer.parseInt(sc.nextLine())));
                        }
                        case "3" -> {
                            System.out.println("Remove item: ");
                            oderService.unChooseItem(restaurant.getMenu().get(Integer.parseInt(sc.nextLine())));
                        }
                        case "4" -> {
                            oderService.getChooses().forEach(v -> System.out.println(v.toString()));
                        }
                        case "5" -> {
                            System.out.println("Total: " + oderService.total());
                        }
                        case "0" -> {
                            return;
                        }
                    }
                } catch (itemExistedException | itemNotFoundException e) {
                    System.out.println(e);
                }
            }
        } catch (restaurantNotFoundException e) {
            System.out.println(e);
        }
    }
}