package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import entity.Product;
import entity.Sale;

public class Shop {
    private List<Product> inventory;
    private List<Sale> sales;

    public Shop() {
        this.inventory = new ArrayList<>();
        this.sales = new ArrayList<>();
    }

    public void addProduct(String name, double price, int stock) {
        Product product = new Product(name, price, stock);
        inventory.add(product);
        saveProductToFile(product);
    }

    public void makeSale(String productName, int quantity) throws Exception {
        Product product = findProduct(productName);
        if (product == null) {
            throw new Exception("Product not found.");
        }
        if (product.getStock() < quantity) {
            throw new Exception("Not enough stock available.");
        }
        Sale sale = new Sale();
        sale.addProduct(product, quantity);
       // Files.add(sale);
        saveSaleToFile(sale);
    }

    public List<Product> getInventory() {
        return inventory;
    }

    private Product findProduct(String name) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null;
    }

    private void saveProductToFile(Product product) {
        try (FileWriter writer = new FileWriter("inventory.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(product.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving product to file: " + e.getMessage());
        }
    }

    private void saveSaleToFile(Sale sale) {
        try (FileWriter writer = new FileWriter("sales.txt", true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(sale.toString());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving sale to file: " + e.getMessage());
        }
    }

    public void loadInventory() {
        try (BufferedReader br = new BufferedReader(new FileReader("inventory.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - \\$");
                String name = parts[0];
                String[] priceAndStock = parts[1].split(" \\(Stock: ");
                double price = Double.parseDouble(priceAndStock[0]);
                int stock = Integer.parseInt(priceAndStock[1].replaceAll("\\)", ""));
                addProduct(name, price, stock);
            }
        } catch (IOException e) {
            System.out.println("Error reading inventory from file: " + e.getMessage());
        }
    }

    public void loadSales() {
        try (BufferedReader br = new BufferedReader(new FileReader("sales.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading sales from file: " + e.getMessage());
        }
    }
}