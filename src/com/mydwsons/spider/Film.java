package com.mydwsons.spider;

public class Film {
	String id = "";
	String title = "";
	String score = "";
	String num = "";
	String quote = "";

	String director = "";
	String actor = "";
	String type = "";
	String date = "";
	String min = "";

	public Film(String id, String title, String score, String num, String quote, String director, String actor,
			String type, String date, String min) {
		super();
		this.id = id;
		this.title = title;
		this.score = score;
		this.num = num;
		this.quote = quote;
		this.director = director;
		this.actor = actor;
		this.type = type;
		this.date = date;
		this.min = min;
	}

	@Override
	public String toString() {
		return "排名   " + id + "\n电影名：" + title + "\n导演：" + director + "\n主演：" + actor + "\n类型：" + type + "\n上映日期："
				+ date + "\n片长：" + min + "\n分数：" + score + "\n打分人数：" + num + "\n引用：" + quote
				+ "\n-----------------------";
	}

}
