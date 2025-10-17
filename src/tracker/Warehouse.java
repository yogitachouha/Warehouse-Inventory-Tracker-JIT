package tracker;
import java.util.*;
public class Warehouse {
	private Map<String, Product> inventory = new HashMap<>();
	private List<StockObserver> observers = new ArrayList<>();

	public void addObserver(StockObserver observer) {
	    observers.add(observer);
	}
	public void addProduct(Product product) {
	    inventory.put(product.getId(), product);
	    System.out.println("✅ Added product: " + product.getName());
	}
	public synchronized void receiveShipment(String productId, int quantity) {
	    Product product = inventory.get(productId);
	    if (product != null) {
	        product.increaseQuantity(quantity);
	        System.out.println("📦 Shipment received: " + quantity + " units of " + product.getName());
	    }
	}
	public synchronized void fulfillOrder(String productId, int quantity) {
	    Product product = inventory.get(productId);
	    if (product.decreaseQuantity(quantity)) {
	        System.out.println("📤 Order fulfilled: " + quantity + " units of " + product.getName());
	        if (product.getQuantity() < product.getThreshold()) {
	            notifyObservers(product);
	        }
	    } else {
	        System.out.println("❌ Not enough stock to fulfill order for " + product.getName());
	    }
	}
	private void notifyObservers(Product product) {
        for (StockObserver observer : observers) {
            observer.onLowStock(product);
        }
    }

}
