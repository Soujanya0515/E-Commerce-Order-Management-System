import java.util.*;

class Product {
    private int id;
    private String name;
    private double price;
    private int stock;
    private String category;

    public Product(int id, String name, double price, int stock, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getCategory() { return category; }
    
    public void reduceStock(int quantity) { stock -= quantity; }
    public void increaseStock(int quantity) { stock += quantity; }
    
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Price: " + price + ", Stock: " + stock + ", Category: " + category;
    }
}

class Order {
    private static int orderCounter = 1;
    private int orderId;
    private List<Product> products;
    private double totalAmount;
    private String status;

    public Order(List<Product> products) {
        this.orderId = orderCounter++;
        this.products = products;
        this.totalAmount = products.stream().mapToDouble(Product::getPrice).sum();
        this.status = "Pending";
    }

    public int getOrderId() { return orderId; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
    public void updateStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return "Order ID: " + orderId + ", Total: " + totalAmount + ", Status: " + status;
    }
}

class Customer {
    private String name;
    private List<Order> orderHistory;
    
    public Customer(String name) {
        this.name = name;
        this.orderHistory = new ArrayList<>();
    }
    
    public void placeOrder(List<Product> products) {
        Order newOrder = new Order(products);
        orderHistory.add(newOrder);
        System.out.println("Order placed: " + newOrder);
    }
    
    public void viewOrderHistory() {
        if (orderHistory.isEmpty()) {
            System.out.println("No previous orders.");
        } else {
            orderHistory.forEach(System.out::println);
        }
    }
}

public class ECommerceSystem {
    public static void main(String[] args) {
        List<Product> inventory = new ArrayList<>();
        inventory.add(new Product(1, "Laptop", 1200, 10, "Electronics"));
        inventory.add(new Product(2, "Phone", 800, 15, "Electronics"));
        
        Customer customer = new Customer("Alice");
        
        List<Product> cart = new ArrayList<>();
        cart.add(inventory.get(0)); // Adding Laptop to cart
        
        customer.placeOrder(cart);
        customer.viewOrderHistory();
    }
}
