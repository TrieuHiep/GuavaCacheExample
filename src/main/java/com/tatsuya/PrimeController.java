package com.tatsuya;
import static spark.Spark.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.LoadingCache;

import spark.Request;
import spark.Response;
import spark.Route;
public class PrimeController {
	public PrimeController(){
		get("/prime", new Route() {
			public Object handle(Request request, Response response) {
				String i = request.queryParams("n");
				int n = Integer.parseInt(i);
				try {
					LoadingCache<Integer,List<Integer>> cache = PrimeService.getLoadingCache();
					System.out.println("Cache Size:" + cache.size());
					return cache.get(n);
				} catch (ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}
		});
	}
}
