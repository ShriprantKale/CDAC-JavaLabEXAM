package userinterface;

import java.util.Scanner;
import service.Shop;
import entity.Product;

public class ShopManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Shop shop = new Shop();
        shop.loadInventory(); 
        int choice;

        do {
            System.out.println("\nShop Management System");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Product");
            System.out.println("3. Make a Sale");
            System.out.println("4. Load Sales");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("Inventory:");
                    for (Product product : shop.getInventory()) {
                        System.out.println(product);
                    }
                    break;
                case 2:
                    try {
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter product stock: ");
                        int stock = scanner.nextInt();
                        shop.addProduct(name, price, stock);
                        System.out.println("Product added successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        System.out.print("Enter product name to sell: ");
                        String productName = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        shop.makeSale(productName, quantity);
                        System.out.println("Sale made successfully!");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    shop.loadSales();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}