package lab2;

import static spark.Spark.*;
import java.util.*;

public class CatalogServer {
	//List holds the books
	private List<Book> catalog = new ArrayList<Book>();
	
	public CatalogServer() {
		//TODO setup catalog
		
		//Book(title,topic,quantity,price,id)
		catalog.add(new Book("Happy Dota Best Dota", "Game",10, 9.55, 1));
	}
	
	//start server
	public void start() {
		port(3154);
		
		//query by item number
		//try IP:3154/queryItem?id=1
		get("/queryItem",(req, res) -> {
			String param = req.queryParams("id");
			int id = Integer.parseInt(param);
			Book b = query(id);
			return "Price="+b.getPrice()+"&Quantity="+b.getQuantity();
		});
		
		//query by topic
		//IP:3154/queryTopic?topic=Game
		get("/queryTopic",(req, res) ->{
			String topic = req.queryParams("topic");
			List<Book> searchResult = query(topic);
			
			String s = "";
			for(Book b:searchResult) {
				s+=b.getTitle()+"\n";
			}
			
			return s;
		});
		
		put("/update",(req, res) -> "Hello World");
	}
	
	//get book by id
	public Book query(int id) {
		for(Book b:catalog) {
			if(b.getItemNumber()==id)
				return b;
		}
		return null;
	}
	
	//get list of books by topic
	public List<Book> query(String topic){
		List<Book> result = new ArrayList<Book>();
		for(Book b:catalog) {
			if(b.getTopic().equals(topic))
				result.add(b);
		}
		return result;
	}
	
	//sell book by id,quantity--
	public void update(int id) {
		Book b = query(id);
		b.sell();
	}
}
