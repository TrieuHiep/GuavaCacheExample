package com.tatsuya;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class PrimeService {
	private static boolean isPrime(int n){
		if(n==2) return true;
		if(n==1||n%2==0) return false;
		for(int i=3;i<=Math.sqrt(n);i+=2){
			if(n%i==0) return false;
		}
		return true;
	}
	private static List<Integer> getPrimes(int n){
		System.out.println("--Generating Primes--");
		List<Integer> list = new ArrayList<Integer>();
		for(int i=1;i<=n;i++){
			if(isPrime(i)){
				list.add(i);
			}
		}
		return list;
	}
	
	public static LoadingCache<Integer, List<Integer>> cache;
	static {
		cache = CacheBuilder.newBuilder().maximumSize(100) // size of cache
				.expireAfterWrite(10, TimeUnit.SECONDS) // time expire
				.build(new CacheLoader<Integer, List<Integer>>() {
					@Override
					public List<Integer> load(Integer id) throws Exception {
						return getPrimes(id);
					}
				});
	}
    public static LoadingCache<Integer, List<Integer>> getLoadingCache() {
		return cache;
    }
}
