package com.mydwsons.spider;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {
	String url;

	public Spider(String url) {
		this.url = url;
	}

	public void start() {
		try {
			String id = "";
			String title = "";
			String director = "";
			String actor = "";
			String date = "";
			String min = "";
			String quote = "";

			// 发送 HTTP GET 请求 获得文档
			Document doc = Jsoup.connect(url).get();

			// 爬取item类中的数据
			Elements items = doc.select(".grid_view .item");
			System.out.println("out:" + items.size());
			// 遍历
			for (int i = 0; i < items.size(); i++) {
				// 获取第i个元素
				Element item = items.get(i);

				// 获取外部的数据
				id = item.select("em").first().text();
				title = item.select(".info .title").first().text();
				quote = item.select(".quote span").first().text();

				// 获取内部地址，并通过地址获得文档
				String seedUrl = item.select(".info a[href]").first().attr("href");
				Document seedDoc;
				if ((seedDoc = testUrlWithTimeOut(seedUrl)) != null) {
					seedDoc = Jsoup.connect(seedUrl).get();
					Element seed = seedDoc.select("#content").first();
					// 开始获取
					director = seed.select("#info .attrs").first().select("a").first().text();
					Elements actEles = seed.select(".actor span span");
					System.out.println(actEles.size());
					for (int j = 0; j < actEles.size(); j++) {
						actor = actor + actEles.select("a").get(i).text();
					}
					date = seed.select("span:has(content)").last().text();
					System.out.println(date);

				} else {
					System.out.println("链接失效");
				}

			}
		} catch (IOException e) {
//			System.out.println("123");
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
