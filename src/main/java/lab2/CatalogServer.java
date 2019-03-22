package lab2;

import static spark.Spark.*;
import static lab2.JsonUtil.*;
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
			
			Map<String,Object> result = new HashMap<String,Object>();//Object!!!!!
			if(b!=null) {
				result.put("quantity", b.getQuantity());
				result.put("price", b.getPrice());
			}
			return result;
		},json());
		
		//query by topic
		//try IP:3154/queryTopic?topic=Game
		get("/queryTopic",(req, res) ->{
			String topic = req.queryParams("topic");
			List<Book> searchResult = query(topic);
			
			Map<String,Object> result = new HashMap<String,Object>();//Object!!!!!
			for(Book b:searchResult) {
				result.put(b.getTitle(), b.getItemNumber());
			}
			return result;
		},json());
		
		put("/update",(req, res) -> {
			String param = req.queryParams("id");
			int id = Integer.parseInt(param);
			if(update(id)) {
				return "book sold";
			}
			else {
				return "????????????????";
			}
		},json());
		
		//changes every response to application/json
		after((req, res) -> {
			  res.type("application/json");
			});
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
	public boolean update(int id) {
		Book b = query(id);
		if(b.getQuantity()>0) {
			b.sell();
			System.out.print("Catalog Server sold book "+b.getTitle()+" and "+b.getQuantity()+"in stock");
			return true;
		}
		return false;
	}
}
