package de.vsis.notizen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class Main {

	static int lastId = -1;
	public static void main(String[] args) {

		// Liste zu verwaltender Notizen
		List<Notiz> notizen = new ArrayList<Notiz>();
		// Zuordnung einer Id zur Notiz
		Map<Integer, Notiz> notizenMap = new HashMap<>();

		// Beispiel einer Methode zum Verarbeiten von GET-Requests
		// Aufruf �ber den Browser: http://localhost:4567/
		
		
		Spark.get("/notizen", new Route() {

			public Object handle(Request req, Response res) {
			    res.type("application/json");
				return  new Gson().toJson(notizen);
			}
		});
		
		Spark.post("/notizen", new Route() {
			
			public Object handle(Request req, Response res) {
			    res.type("application/json");
				Notiz notiz = new Gson().fromJson(req.body(), Notiz.class);
				++ lastId;
				notiz.setId(lastId);
				notizen.add(notiz);
				notizenMap.put(notiz.getId(), notiz);
				return new Gson().toJson(notiz);
				
			}
		});
		
		Spark.get("/notizen/:id",  new Route() {
			
			public Object handle(Request req, Response res) {
				res.type("application/json");
				Notiz result = notizenMap.get(Integer.parseInt(req.params(":id")));
				return new Gson().toJson(result);
			}
		});
		
		Spark.delete("/notizen/:id", new Route() {
			
			public Object handle(Request req, Response res) {
				res.type("application/json");
				int id = Integer.parseInt(req.params(":id"));
				Notiz toDelete = notizenMap.get(id);
				notizen.remove(toDelete);
				notizenMap.remove(id);
				return new Gson().toJson(toDelete);
			}
			
		});


	}

}
