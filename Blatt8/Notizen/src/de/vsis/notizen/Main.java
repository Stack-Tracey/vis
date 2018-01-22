package de.vsis.notizen;

import java.util.ArrayList;
import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

public class Main {

	public static void main(String[] args) {

		// Liste zu verwaltender Notizen
		List<Notiz> notizen = new ArrayList<Notiz>();

		// Beispiel einer Methode zum Verarbeiten von GET-Requests
		// Aufruf über den Browser: http://localhost:4567/
		
		Spark.get("/", new Route() {

			public Object handle(Request req, Response res) {

				return "Template für eigenen Code";
			}
		});


	}

}
