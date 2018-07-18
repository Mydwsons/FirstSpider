package com.mydwsons.spider;

public class Film {
	String id = "";
	String title = "";
	String director = "";
	String actor = "";
	String date = "";
	String min = "";
	String quote = "";

	public Film(String id, String title, String director, String actor, String date, String min, String quote) {
		super();
		this.id = id;
		this.title = title;
		this.director = director;
		this.actor = actor;
		this.date = date;
		this.min = min;
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "Film  " + id + "\ntitle:" + title + "\ndirector:" + director + "\nactor:" + actor + "\ndate:" + date
				+ "\nmin:" + min + "\nquote:" + quote + "\n-----------------------";
	}

}
