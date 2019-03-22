package lab2;

import static spark.Spark.*;
import static lab2.JsonUtil.*;
import static lab2.HttpUtil.*;
import java.util.*;


public class OrderServer {
	
	//TODO order log??????
	
	public void start() {
		port(3800);
		
		get("/buy",(req, res) ->{
			//TODO
			Response queryResponse = request("GET","http://localhost:3154/queryItem?id=1");
			Map<String,Object> result = queryResponse.json();
			
			if((double)result.get("quantity")>0) {//TODO double?????????????????
				Response buyResponse = request("PUT","http://localhost:3154/update?id=1");
				return buyResponse.status;
			}
			return null;
		},json());
		
		after((req, res) -> {
			res.type("application/json");
		});
	}
	
}
