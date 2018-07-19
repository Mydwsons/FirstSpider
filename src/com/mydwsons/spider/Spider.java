package com.mydwsons.spider;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {
	String url;

	public Spider(String url) {
		this.url = url;
	}

	public String start() {
		try {
			// out
			String id = "";
			String title = "";
			String score = "";
			String num = "";
			String quote = "";

			// in
			String director = "";
			String actor = "";
			String type = "";
			String date = "";
			String min = "";

			String temp = "";

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
			System.out.println("out:" + items.size());
			// 遍历
			for (int i = 0; i < items.size(); i++) {
				// 获取第i个元素
				Element item = items.get(i);

				// 获取out数据
				id = item.select("em").first().text();
				title = item.select(".info .title").first().text();

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
				
				
				Film film = new Film(id, title, score, num, quote, director, actor, type, date, min);
				System.out.println(film.toString());
			}
			try {
				temp = doc.select(".next a").first().attr("href");
				return temp;
			} catch (Exception e) {
				return null;
			}
		} catch (IOException e) {
//			System.out.println("123");
			e.printStackTrace();
		}
		return null;
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
