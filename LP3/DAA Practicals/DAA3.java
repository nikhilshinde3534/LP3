//package DAA;
import java.util.Arrays;
class DAA3 {
    static class ItemValue {
        double value;
        double weight;

        ItemValue(double value, double weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    static double fractionalKnapsack(int capacity, ItemValue[] items, int n) {
        double totalValue = 0.0;

        Arrays.sort(items, (a, b) -> (int) (b.value / b.weight - a.value / a.weight));

        for (int i = 0; i < n; i++) {
            if (capacity >= items[i].weight) {
                capacity -= items[i].weight;
                totalValue += items[i].value;
            } else {
                totalValue += (items[i].value / items[i].weight) * capacity;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        int capacity = 50;
        ItemValue[] items = {
                new ItemValue(50, 10),
                new ItemValue(200, 20),
                new ItemValue(120, 60)
        };

        int n = items.length;
        System.out.println("Maximum value: " + fractionalKnapsack(capacity, items, n));
    }
}
