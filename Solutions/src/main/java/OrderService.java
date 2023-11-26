import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private final List<Item> listChoose = new ArrayList<>();

    public void chooseItem(Item item) throws itemExistedException {
        Item itemChoose = listChoose.stream().filter(v -> item.getName().equalsIgnoreCase(v.getName())).findAny().orElseGet(() -> null);
        if (itemChoose == null) {
            listChoose.add(item);
            return;
        }
        throw new itemExistedException(item.getName());
    }

    public void unChooseItem(Item item) throws itemNotFoundException {
        Item itemChoose = listChoose.stream().filter(v -> item.getName().equalsIgnoreCase(v.getName())).findAny().orElseGet(() -> null);
        if (itemChoose != null) {
            listChoose.remove(item);
            return;
        }
        throw new itemNotFoundException(item.getName());
    }

    public int total() {
        return listChoose.stream().mapToInt(Item::getPrice).sum();
    }

    public List<Item> getChooses() {
        return listChoose;
    }

}
