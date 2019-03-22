package lab2;
import static spark.Spark.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		//Test server = new Test();
		CatalogServer server = new CatalogServer();
		server.start();
	}

}
