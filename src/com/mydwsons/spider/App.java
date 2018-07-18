package com.mydwsons.spider;

public class App {
	public static void main(String[] args) {
		Spider spider = new Spider("https://movie.douban.com/top250");
		spider.start();
	}
	
	
}
