package lab2;

public class Book {
	private String title;
	private String topic;
	private int quantity;
	private double price;
	private int item_number;
	
	//constructor
	public Book(String title,String topic,int quantity,double price,int num) {
		this.title=title;
		this.topic=topic;
		this.quantity=quantity;
		this.price=price;
		item_number = num;
	}
	
	//getters
	public String getTitle() {
		return title;
	}
	public String getTopic() {
		return topic;
	}
	public int getQuantity() {
		return quantity;
	}
	public double getPrice() {
		return price;
	}
	public int getItemNumber() {
		return item_number;
	}
	
	//setter
	public void sell() {
		quantity -= 1;
	}
	
}
