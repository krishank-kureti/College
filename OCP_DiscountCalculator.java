import java.util.HashMap;
import java.util.Map;

class DiscountCalculatorBad {
    public double calculateDiscount(double price, String productType) {
        if (productType.equals("ELECTRONICS")) {
            return price * 0.15;
        } else if (productType.equals("CLOTHING")) {
            return price * 0.20;
        } else if (productType.equals("GROCERIES")) {
            return price * 0.05;
        }
        return 0;
    }
}

interface DiscountStrategy {
    double applyDiscount(double price);
    String getProductType();
}

class ElectronicsDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.15;
    
    @Override
    public double applyDiscount(double price) {
        return price * DISCOUNT_RATE;
    }
    
    @Override
    public String getProductType() {
        return "ELECTRONICS";
    }
}

class ClothingDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.20;
    
    @Override
    public double applyDiscount(double price) {
        return price * DISCOUNT_RATE;
    }
    
    @Override
    public String getProductType() {
        return "CLOTHING";
    }
}

class GroceriesDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.05;
    
    @Override
    public double applyDiscount(double price) {
        return price * DISCOUNT_RATE;
    }
    
    @Override
    public String getProductType() {
        return "GROCERIES";
    }
}

class BooksDiscount implements DiscountStrategy {
    private static final double DISCOUNT_RATE = 0.10;
    
    @Override
    public double applyDiscount(double price) {
        return price * DISCOUNT_RATE;
    }
    
    @Override
    public String getProductType() {
        return "BOOKS";
    }
}

class DiscountCalculator {
    private Map<String, DiscountStrategy> discountStrategies;
    
    public DiscountCalculator() {
        discountStrategies = new HashMap<>();
    }
    
    public void registerDiscountStrategy(DiscountStrategy strategy) {
        discountStrategies.put(strategy.getProductType(), strategy);
    }
    
    public double calculateDiscount(double price, String productType) {
        DiscountStrategy strategy = discountStrategies.get(productType);
        if (strategy == null) {
            System.out.println("No discount available for: " + productType);
            return 0;
        }
        return strategy.applyDiscount(price);
    }
    
    public double calculateFinalPrice(double price, String productType) {
        double discount = calculateDiscount(price, productType);
        return price - discount;
    }
}

class Product {
    private String name;
    private String type;
    private double price;
    
    public Product(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public double getPrice() {
        return price;
    }
}

public class OCP_DiscountCalculator {
    public static void main(String[] args) {
        System.out.println("===== Open/Closed Principle (OCP) =====\n");
        
        DiscountCalculator calculator = new DiscountCalculator();
        
        calculator.registerDiscountStrategy(new ElectronicsDiscount());
        calculator.registerDiscountStrategy(new ClothingDiscount());
        calculator.registerDiscountStrategy(new GroceriesDiscount());
        
        Product laptop = new Product("Laptop", "ELECTRONICS", 1000);
        Product shirt = new Product("T-Shirt", "CLOTHING", 50);
        Product milk = new Product("Milk", "GROCERIES", 5);
        
        System.out.println("Price: ₹" + laptop.getPrice() + 
                         " -> Final Price: ₹" + calculator.calculateFinalPrice(laptop.getPrice(), laptop.getType()));
        System.out.println("Price: ₹" + shirt.getPrice() + 
                         " -> Final Price: ₹" + calculator.calculateFinalPrice(shirt.getPrice(), shirt.getType()));
        System.out.println("Price: ₹" + milk.getPrice() + 
                         " -> Final Price: ₹" + calculator.calculateFinalPrice(milk.getPrice(), milk.getType()));
        
        System.out.println("\n--- Adding new product type: BOOKS ---");
        calculator.registerDiscountStrategy(new BooksDiscount());
        
        Product book = new Product("Design Patterns", "BOOKS", 45);
        System.out.println("Price: ₹" + book.getPrice() + 
                         " -> Final Price: ₹" + calculator.calculateFinalPrice(book.getPrice(), book.getType()));
    }
}
