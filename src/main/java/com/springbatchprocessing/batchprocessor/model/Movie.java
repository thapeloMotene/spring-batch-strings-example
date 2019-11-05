package com.springbatchprocessing.batchprocessor.model;


public class Movie {
	String id;
	String title;
	String genres;
	
	public Movie(String id, String title, String genres) {
		super();
		this.id = id;
		this.title = title;
		this.genres = genres;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenres() {
		return genres;
	}

	public void setGenres(String genres) {
		this.genres = genres;
	}
	
	
	
}
