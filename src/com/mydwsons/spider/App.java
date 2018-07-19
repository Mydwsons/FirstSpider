package com.mydwsons.spider;

import java.io.FileWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

public class App {
	public static void main(String[] args) {

		// 分析页面数据
		List<Film> films = Collections.synchronizedList(new LinkedList<>());
		ExecutorService pool = Executors.newCachedThreadPool();
		String url = "";
		for (int i = 0; i < 10; i++) {
			url = String.format("https://movie.douban.com/top250?start=%d", i * 25);
			pool.execute(new Spider(url, films));
		}
		pool.shutdown();

		// 排序数据并将数据写到本地
		while (true) {
			if (pool.isTerminated()) {
				writeData(films);
				break;
			} else {
				try {
					Thread.sleep(1_000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static void writeData(List<Film> films) {
		System.out.println(films.size());
		Collections.sort(films);

		for (Film film : films) {
			System.out.println(film);
		}
		String json = new Gson().toJson(films);
		try (FileWriter out = new FileWriter("250.json")) {
			out.write(json);
			System.out.println("JSON WRITE OVER");
		} catch (Exception e) {
			e.printStackTrace();
		}

		ExecutorService pool = Executors.newFixedThreadPool(4);

		for (Film film : films) {
			pool.execute(new ImgLoader(film));
		}
	}
}
