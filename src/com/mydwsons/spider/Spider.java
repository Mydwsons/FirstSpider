package com.mydwsons.spider;

import java.io.IOException;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider implements Runnable {
	private String url;
	private List<Film> films;

	public Spider(String url, List<Film> films) {
		this.url = url;
		this.films = films;
	}

	public void run() {
		try {
			// out
			int id = 0;
			String title = "";
			String score = "";
			String num = "";
			String quote = "";
			String path = "";

			// in
			String director = "";
			String actor = "";
			String type = "";
			String date = "";
			String min = "";

			// 发送 HTTP GET 请求 获得文档
			Connection conn = Jsoup.connect(url);
			conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
			conn.header("Accept-Encoding", "gzip, deflate, sdch");
			conn.header("Accept-Language", "zh-CN,zh;q=0.8");

			Document doc = conn.header("User-Agent",
					"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
					.get();

			// 爬取item类中的数据
			Elements items = doc.select(".grid_view .item");
//			System.out.println("out:" + items.size());
			// 遍历
			for (int i = 0; i < items.size(); i++) {
				// 获取第i个元素
				Element item = items.get(i);

				// 获取out数据
				id = Integer.parseInt(item.select("em").first().text());
				title = item.select(".info .title").first().text();
				path = item.select(".pic img[src]").first().attr("src");

				score = item.select(".star .rating_num").text();
				num = item.select(".star span").last().text();
				char[] arr = num.toCharArray();
				num = new String(arr, 0, arr.length - 3);
				try {
					quote = item.select(".quote span").first().text();
				} catch (Exception e) {
				}

				// 获取in数据
				String seedUrl = item.select(".info .hd a").first().attr("href");
				Connection seedConn = Jsoup.connect(seedUrl);
				conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
				conn.header("Accept-Encoding", "gzip, deflate, sdch");
				conn.header("Accept-Language", "zh-CN,zh;q=0.8");

				Document seedDoc = seedConn.header("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36")
						.get();

				Elements seed = seedDoc.select("#info");
				director = seed.select(".attrs a[rel$=v:directedBy]").text();
				actor = seed.select(".actor .attrs").text();
				type = seed.select("span[property$=v:genre]").text();
				date = seed.select("span[property$=v:initialReleaseDate]").text();
				min = seed.select("span[property$=v:runtime]").text();

				Film film = new Film(id, title, score, num, quote, director, actor, type, date, min, path);

				films.add(film);
				System.out.println(Thread.currentThread().getName() + " GET " + id);
			}
			System.out.println("SPIDER " + Thread.currentThread() + " OVER");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static Document testUrlWithTimeOut(String urlString) {

		try {
			Document seedDoc = Jsoup.connect(urlString).get();
//			System.out.println("链接可用");
			return seedDoc;
		} catch (Exception e1) {
			return null;
		}
	}

}
