package tracker;

public class AlertService implements StockObserver{
	@Override
	 public void onLowStock(Product product) {
	        System.out.println("⚠️ Restock Alert: Low stock for " + product.getName());
	        System.out.println("--------------------------------------------------");
	    }
}
