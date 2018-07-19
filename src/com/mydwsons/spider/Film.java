package com.mydwsons.spider;

public class Film implements Comparable<Film> {
	int id = 0;
	String title = "";
	String score = "";
	String num = "";
	String quote = "";

	String director = "";
	String actor = "";
	String type = "";
	String date = "";
	String min = "";
	String path = "";

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Film(int id, String title, String score, String num, String quote, String director, String actor,
			String type, String date, String min, String path) {
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
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	@Override
	public String toString() {
		return "排名   " + id + "\n电影名：" + title + "\n导演：" + director + "\n主演：" + actor + "\n类型：" + type + "\n上映日期："
				+ date + "\n片长：" + min + "\n分数：" + score + "\n打分人数：" + num + "\n引用：" + quote
				+ "\n-----------------------";
	}

	@Override
	public int compareTo(Film o) {
		// TODO Auto-generated method stub
		return this.id - o.id;
	}

}
