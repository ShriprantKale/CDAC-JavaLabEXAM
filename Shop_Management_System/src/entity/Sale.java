package entity;

import java.util.ArrayList;
import java.util.List;

public class Sale {


    private List<Product> products;

    public Sale() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
        product.reduceStock(quantity);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Sale Items:\n");
        for (Product product : products) {
            sb.append(product.getName()).append("\n");
        }
        sb.append("Total: $").append(calculateTotal());
        return sb.toString();
    }
}

