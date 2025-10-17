package tracker;

public class Main {
	 public static void main(String[] args) {
	        Warehouse warehouse = new Warehouse();
	        AlertService alertService = new AlertService();
	        warehouse.addObserver(alertService);

	        Product laptop = new Product("P001", "Laptop", 0, 5);
	        warehouse.addProduct(laptop);
	        
	        Thread shipmentThread = new Thread(() -> warehouse.receiveShipment("P001", 10));
	        Thread orderThread = new Thread(() -> warehouse.fulfillOrder("P001", 6));
	        
	        shipmentThread.start();
	        orderThread.start();
	        
	        try {
	            shipmentThread.join();
	            orderThread.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
}
}