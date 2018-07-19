package com.mydwsons.spider;

public class App {
	public static void main(String[] args) {
		String url = "https://movie.douban.com/top250";
		String temp = "";
		while (temp != null) {
			Spider spider = new Spider(url + temp);
			temp = spider.start();
		}
	}
}
